package models;

import java.util.List;
import java.util.stream.Collectors;

import twitter4j.HashtagEntity;

/**
 * Defines parameters of a Tweet.
 *
 * @author Jaskaran Singh
 * @author Gagandeep Singh
 */

public class Tweet {
	
	private String screenName;
	private String tweet;
	private List<String> hashtags;
	private String location;
	private Double latitude;
	private Double longitude;
	
	/**
	 * Default Constructor
	 */
	public Tweet() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Parameterized Constructor
	 * 
	 * @param screenName of user of type String
	 * @param tweet text of type String
	 * @param location of tweet of type String
	 * @param latitude of location of type String
	 * @param longitude of location of type String
	 * @param list of hashtags of type HashTagEntity
	 */
	public Tweet(String screenName,String tweet,String location,Double latitude,Double longitude,List<HashtagEntity> hashtags) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.screenName = screenName;
		this.tweet = tweet;
		this.location = location;
		this.hashtags = hashtags.stream().map(HashtagEntity::getText).collect(Collectors.toList());
	}
	
	/**
     * Gets latitude of geolocation.
     *
     * @return latitude of type double
     */
	
	public Double getLatitude() {
		return latitude;
	}

	/**
     * Sets latitude of geolocation.
     *
     * @param latitude of type double
     */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	/**
     * Gets longitude of geolocation.
     *
     * @return longitude of type double
     */
	public Double getLongitude() {
		return longitude;
	}

	/**
     * Sets longitude of geolocation.
     *
     * @param longitude of type double
     */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
     * Gets Location of the user.
     *
     * @return location of type String
     */
	public String getLocation() {
		return location;
	}
	
	/**
     * Sets Location of the user.
     *
     * @param string of type String
     */
	public void setLocation(String location) {
		this.location = location;
	}

	
	/**
     * Gets Hashtags associated with tweet.
     *
     * @return hashtags of type List<HashtagEntity>
     */
	public List<String> getHashtags() {
		return hashtags;
	}

	/**
     * Sets Hashtags associated with tweet.
     *
     * @param hashtags of type List<HashtagEntity>
     */
	public void setHashtags(List<HashtagEntity> hashtags) {
		this.hashtags = hashtags.stream().map(HashtagEntity::getText).collect(Collectors.toList());
	}

	/**
     * Gets Screen name of the user profile.
     *
     * @return screenName of type String
     */
	
	public String getScreenName() {
		return screenName;
	}
	
	
	/**
     * Sets screen name of the user profile.
     *
     * @param screenName of type String
     */
	
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	
	
	/**
     * Gets text of the tweet.
     *
     * @return tweet of type String
     */
	public String getTweet() {
		return tweet;
	}
	
	/**
     * Sets text of the tweet.
     *
     * @param tweet of type String
     */
	public void setTweet(String tweet) {
		this.tweet = tweet;
	}
	
	
}
