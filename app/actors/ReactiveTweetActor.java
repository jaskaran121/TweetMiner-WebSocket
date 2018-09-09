package actors;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.google.inject.Inject;


import akka.actor.AbstractActorWithTimers;
import akka.actor.ActorRef;
import akka.actor.Props;
import models.SearchResults;
import models.Tweet;
import scala.concurrent.duration.Duration;
import service.ActualTwitterApi;
import service.TwitterApi;
import twitter4j.Query;
import twitter4j.Status;
import models.*;
/**
 * Actor for retrieving tweets based on keyword.
 * 
 * @author Jaskaran
 * @author Gagan
 */

public class ReactiveTweetActor extends AbstractActorWithTimers {

	private Map<String,ActorRef> userActors = new LinkedHashMap<>();
	 private TwitterApi twitterService ;
	 
	 /**
		 * Parameterized Constructor
		 * 
		 * @param  twitterService a instance of type TwitterApi. 
		 */
	public ReactiveTweetActor(TwitterApi twitterService) {
	
		this.twitterService = twitterService;
	}

	
	/**
     * Handles search keyword messages.
     * 
     */
	@Override
	public Receive createReceive() {
		
		return receiveBuilder()
				.match(RegisterSearchKeyword.class, register -> userActors.put(register.keyword,sender()))
				.match(Tick.class, msg -> notifyClients())
				.build();
	}
	
	/**
     * Sends messages to all the subscribed users.
     * 
     */
	private void notifyClients() {
		userActors.forEach((key,value)-> { 
			CompletionStage<List<Status>> fetchedTweets = fetchTweets(key);
			CompletionStage<String> emotion = fetchedTweets.thenApply(i->DataProcessingOnSearchTweets.setEmotionCount(i));
			SearchResults s =  new SearchResults(key,filterTweets(fetchedTweets),emotion);
UserActor.TweetDataMessage tMsg = new UserActor.TweetDataMessage(s);
value.tell(tMsg,self());
});
	}
	
	/**
	 * Filters the fetched tweets and collects 
	 * to an list of type CompletionStage.
	 *  
	 * @param  List<Status> of type CompletionStage
	 * @return List<Tweet> of type CompletionStage
	 */
	private CompletionStage<List<Tweet>> filterTweets(CompletionStage<List<Status>> fetchTweets) {
		CompletionStage<List<Tweet>> tweets = 
				fetchTweets.thenApply(tw->tw.stream().limit(10).
			map(status -> {
				String location = "";
				Double longitude = 0.0;
				Double latitude = 0.0;
				if(status.getPlace()!=null)
				{
					location = status.getPlace().getFullName();
					latitude = status.getPlace().getBoundingBoxCoordinates()[0][0].getLatitude();
					longitude = status.getPlace().getBoundingBoxCoordinates()[0][0].getLongitude();
				}
				 return new Tweet(status.getUser().getScreenName(),status.getText(),location,latitude,longitude,
					Arrays.asList(status.getHashtagEntities()));}).collect(Collectors.toList()));
		return tweets;
	}
	
	
	/**
	 * Fetches tweets from twitter4j api.
	 *  
	 * @param  keyword to be searched of type String
	 * @return List<Status> of type CompletionStage
	 */
	private CompletionStage<List<Status>> fetchTweets(String keyword) {
		Query q=new Query(keyword);
			q.setCount(100);
			return CompletableFuture.supplyAsync(()->twitterService.getQueryResult(q));
		
	}

	public static final class Tick {
	}
	
	/**
	 * Sets reactive time interval, after which tweets are 
	 * fetched.
	 */
	@Override
	public void preStart() {
		getTimers().startPeriodicTimer("Timer", new Tick(), Duration.create(7, TimeUnit.SECONDS));
	}
	
	/**
     * This class is used to register keyword 
     * to be searched.
     */
	public static class RegisterSearchKeyword {
		private final String keyword;
		public RegisterSearchKeyword(String keyword) {
			this.keyword = keyword;
		}
	}
	
	
	/**
     * Forms props to create ReactiveTweetActor.
     * 
     * @param  twitterService a instance of type TwitterApi 
     * @return Newly created props
     */
	public static Props getProps(TwitterApi twitterService) {
		return Props.create(ReactiveTweetActor.class,twitterService );
	}

}
