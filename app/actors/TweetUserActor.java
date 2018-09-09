package actors;

import java.util.concurrent.CompletableFuture;

import models.*;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import service.TwitterApi;
import twitter4j.*;

/**
 * Actor for retrieving information of owner of tweet.
 * 
 * @author Gagan
 */
public class TweetUserActor extends AbstractActor {
	
	TwitterApi twitterService;
	
	/**
	 * Parameterized Constructor
	 * 
	 * @param  twitterService a instance of type TwitterApi 
	 */
	public TweetUserActor(TwitterApi twitterService) {
		
		this.twitterService=twitterService;
	}
	
	/**
     * This class is used to store name, of the user.
     */
	    public static class TweetUserActorProtocolGetUser {
	        public final String name;

	        public TweetUserActorProtocolGetUser(String name) {
	            this.name = name;
	        }
	    }
	    
	    /**
	     * This class is used to store name, of the user.
	     */
	    public static class TweetUserActorProtocolGetTimeLine {
	        public final String name;

	        public TweetUserActorProtocolGetTimeLine(String name) {
	            this.name = name;
	        }
	    }

	    /**
	     * Forms props to create TweetUserActor
	     * 
	     * @param  twitterService a instance of type TwitterApi 
	     * @return Newly created props
	     */
	public static Props getProps(TwitterApi twitterService) {
		return Props.create(TweetUserActor.class,twitterService);
	}
	
	
	 /**
     * Handles user data and user status messages.
     * 
     * User data message - Returns message of User of type CompletableFuture to
     * home controller.
     * 
     * User status message - Returns message of top tweets by user as List<Status>
     * of type CompletableFuture to home controller.
     */
	@Override
	public Receive createReceive() {
		// TODO Auto-generated method stub
		return receiveBuilder()
				.match(TweetUserActorProtocolGetUser.class, hello -> 
					sender().tell(DataProcessingOnSearchTweets.getUserData(hello.name,twitterService), self())
		        )
				.match(TweetUserActorProtocolGetTimeLine.class, hello -> {
				sender().tell(DataProcessingOnSearchTweets.getUser20TopTweets(hello.name,twitterService), self());
		        })
		        .build();
	}


}