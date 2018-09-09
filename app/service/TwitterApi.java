package service;


import java.util.List;
import twitter4j.Query;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.User;

/**
 * Twitter Api class interface
 * 
 * @author Amankirat
 * @author Prabhleen
 */
public interface TwitterApi {

	public List<Status> getQueryResult(Query q) ;

	public User showUser(String screenName) throws TwitterException;
	
	public List<Status> getUserTimeline(String screenName) throws TwitterException;
	
}
