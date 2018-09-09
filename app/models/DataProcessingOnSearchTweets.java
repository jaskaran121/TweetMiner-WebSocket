package models;

import java.util.Arrays;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

import service.TwitterApi;
import twitter4j.GeoLocation;
import twitter4j.Query;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.User;

/**
 * This DataProcessingOnSearchTweets program contains all the  
 * logics applied on data fetched from twitter4jApi.
 * 
 * @author Jaskaran Singh
 * @author Gagandeep Singh
 */
public class DataProcessingOnSearchTweets {
	
	
	/**
	 * Queries getQueryResult method of Twitter API class 
	 * to fetch tweets.
	 * 
	 * @param  searchKeyword of Type String
	 * @param  twitterService a instance of type TwitterApi    
	 * @return List<Status> of type CompletableFuture
	 * @author Jaskaran
	 */
	public static CompletableFuture<List<Status>> searchHashTag(String searchKeyword,TwitterApi twitterService)
	
	{
		Query q=new Query("#"+searchKeyword);
		q.setCount(10);
		CompletableFuture<List<Status>> futureTweet = CompletableFuture.supplyAsync(() -> twitterService.getQueryResult(q));
       
        return futureTweet ;
	
	}
	
	/** 
	 * Queries getUserTimeline method of TwitterApi class 
	 * to fetch tweets.
	 * 
	 * @param  screenName of owner of type String  
	 * @param  twitterService a instance of type TwitterApi   
	 * @return List<Status> of type CompletableFuture
	 * @author Gagandeep
	 * @throws TwitterException It throws a TwitterException
	 */
	public static CompletableFuture<List<Status>> getUser20TopTweets(String screenName,TwitterApi twitterService) throws TwitterException{
		return CompletableFuture.supplyAsync(() -> {
			List<Status> recentPost = null;
			try {
				recentPost = twitterService.getUserTimeline(screenName);
			} catch (TwitterException exp) {
				
			}
			return recentPost.stream().limit(10).collect(Collectors.toList());
		});
	}
	
	/**
	 * Queries showUser method of Twitter API class
	 * to fetch personal data of tweet owner.
	 * 
	 * @param  screenName of owner of type String
	 * @param  twitterService a instance of type TwitterApi   
	 * @return User of type CompletableFuture
	 * @author Gagandeep
	 * @throws TwitterException It throws a TwitterException
	 */
	
	public static CompletableFuture<User> getUserData(String screenName,TwitterApi twitterService) throws TwitterException
	{
		return CompletableFuture.supplyAsync(() -> {
			User user = null;
			try {
				
				user = twitterService.showUser(screenName);
			} catch (TwitterException exp) {
				
			}
			return user;
		});
		
		
	}
	
	
	/**
	 * Queries getQueryResult method of TwitterApi class 
	 * to fetch tweets by location.
	 * 
	 * @param  latitude of Type Double  
	 * @param  longitude of Type Double
	 * @param  twitterService a instance of type TwitterApi   
	 * @return List<String> of type CompletableFuture
	 * @author Amankirat
	 */
	public static CompletableFuture<List<String>> searchLocationTweets(Double latitude,Double longitude,TwitterApi twitterService)
	{
		CompletableFuture<List<Status>> tweets = null;
		tweets = CompletableFuture.supplyAsync(()->{
				GeoLocation geo = new GeoLocation(latitude,longitude);
					Query q= new Query();
					q.geoCode(geo, 500, "1km");
					q.count(10);
				return twitterService.getQueryResult(q);
			});
		CompletableFuture<List<String>> finalone = null;
			finalone = tweets.thenApply(i->i.stream().map(j->j.getText()).collect(Collectors.toList()));
			return finalone;
	}
	
	
	
	/**
	 * Determines sentiments of list of tweets.
	 * 
	 * Calculates happytweet,sadtweet,neutral tweet count. 
	 * 
	 * @param  list of tweets of type Status
	 * @return string of emotion with highest count
	 * @author Prabhleen
	 */	
public static String setEmotionCount(List<Status> tweets) {
		
		
		Long happycount = tweets.stream().map(i->i.getText()).filter(st->(st.contains(":-)")||st.contains(":)")) && !(st.contains(":-(")||st.contains(":("))).count();
		Long sadcount = tweets.stream().map(i->i.getText()).filter(st->(st.contains(":-(")||st.contains(":(")) && !(st.contains(":-)")||st.contains(":)"))).count();
		Long neutralcount =tweets.stream().map(i->i.getText()).filter(st->!(st.contains(":-(")||st.contains(":(")) && !(st.contains(":-)")||st.contains(":)"))).count();
		
		Long total=happycount+sadcount+neutralcount;
		
		if(total!=0) {
		if(((happycount*100)/total)>70) {
			return ":-)";
		}
		else if(((sadcount*100)/total)>70) {
			return ":-(";
		}
		else {
			return ":-|";
		}
		}
		else {return ":-|";}
			
		}

/**
 * Calculates count of each word,mapping of 
 * word with its count.
 * Sorting this Map in descending order.
 * 
 * @param  searchKeyword location to be searched of type String 
 * @param  twitterService a instance of type TwitterApi    
 * @return LinkedHashMap<String,Long> of type CompletableFuture
 * @author Gurdip
 */
public static CompletableFuture<LinkedHashMap<String,Long>> createWordCount(String keyword,TwitterApi twitterService)
{
	
	CompletableFuture<List<Status>> tweets = null;
	Query query = new Query(keyword);
	query.setCount(100);
	
		tweets = CompletableFuture.supplyAsync(()->twitterService.getQueryResult(query));
		
CompletableFuture<String[]> str=tweets.thenApply(i->i.stream().map(j->j.getText()).reduce("",(t1,t2)->t1+t2).replaceAll("[^\\w\\s]", "").trim().replaceAll(" +", " ").split("\\s"));
	 

CompletableFuture<LinkedHashMap<String,Long>> myMap=	
		str.thenApply(q->Arrays.stream(q).map(i->i.toLowerCase()).
				collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed()).
				collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(oldvalue,newvalue)->oldvalue,LinkedHashMap::new)));

return myMap;
}
}
