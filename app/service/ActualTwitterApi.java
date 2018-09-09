package service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Function;
import javax.inject.Singleton;

import com.google.inject.Inject;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;


import models.Tweet;

import play.mvc.Result;
import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;
import java.util.Map;

/**
 * This ActualTwitterApi program queries the 
 * Twitter4jApi to fetch data.
 * 
 * @author Jaskaran Singh
 * @author Gagandeep Singh
 */
@Singleton
public class ActualTwitterApi implements TwitterApi{
	
	private final Config config;
	
	private final Twitter twitter;
	
	/**
	 * This Default Constructor loads configuration to create  
	 * twitter instance for authorization of Twitter4J API.
	 */
	public ActualTwitterApi() {
		config = ConfigFactory.load();

		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey(config.getString("CONSUMER_KEY"))
				.setOAuthConsumerSecret(config.getString("CONSUMER_SECRET"))
				.setOAuthAccessToken(config.getString("ACCESS_TOKEN"))
				.setOAuthAccessTokenSecret(config.getString("ACCESS_TOKEN_SECRET"));
		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter = tf.getInstance();
	}
	
	
	/**
	 * Queries the TwitterAPI to fetch tweets.
	 * 
	 * @param  searchString instance of type Query.          
	 * @return list of tweets that match a specified query of type Status.
	 * @author Jaskaran
	 * @throws TwitterException It throws a TwitterException
	 */
	@Override
	public List<Status> getQueryResult(Query q){
	List<Status> tweets= null;
	
	try {
			tweets = twitter.search(q).getTweets();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tweets;
	
	}
	
	/** 
	 * Queries Twitter4j API to fetch personal data of tweet owner.
	 * 
	 * @param  screenName of owner of type String.      
	 * @return extended information of a given user of type User.
	 * @author Gagandeep
	 * @throws TwitterException It throws a TwitterException
	 */
	@Override
	public User showUser(String screenName) throws TwitterException
	{
		return twitter.showUser(screenName);	
	}
	
	/** 
	 * Queries Twitter4j API to fetch 20 most recent status 
	 * posted from the authenticating user.
	 * 
	 * @param  screenName of owner of type String.      
	 * @return list of type Status.
	 * @author Gagandeep
	 * @throws TwitterException It throws a TwitterException
	 */
	@Override
	public List<Status> getUserTimeline(String screenName) throws TwitterException{
		return (List<Status>)twitter.getUserTimeline(screenName);
	}
	
	

}
