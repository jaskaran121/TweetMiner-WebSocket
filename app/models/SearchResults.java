/**
 * 
 */
package models;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class SearchResults {
	
	/**
	 * Defines parameters of a Searched data.
	 *
	 * @author Jaskaran Singh
	 * @author Gagandeep Singh
	 */
	private String keyword;
	private CompletionStage<List<Tweet>> tweet;
	private CompletionStage<String> emotion;
	
	
	/**
	 * Default Constructor
	 */
	public SearchResults()
	{
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Parameterized Constructor
	 * 
	 * @param keyword to be searched of type String
	 * @param tweet a list of type CompletionStage<List<Tweet>>
	 * @param emotion parameter of type CompletionStage<String>
	 */
	public SearchResults(String keyword,CompletionStage<List<Tweet>> tweet,CompletionStage<String> emotion) {
		this.emotion=emotion;
		this.keyword = keyword;
		this.tweet = tweet;
	}
	
	/**
     * Gets sentiment parameter of tweet.
     *
     * @return emotion of type CompletionStage<String>
     */
	public CompletionStage<String> getEmotion() {
		return emotion;
	}
	
	/**
     * Sets sentiment of searched tweets.
     *
     * @param CompletionStage<String> of type emotion
     */
	public void setEmotion(CompletionStage<String> emotion) {
		this.emotion = emotion;
	}
	
	/**
     * Gets keyword to be searched.
     *
     * @return keyword of type String
     */
	public String getKeyword() {
		return keyword;
	}
	
	/**
     * Sets keyword to be searched.
     *
     * @param keyword of type String
     */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	/**
     * Gets list of tweets.
     *
     * @return tweet of type CompletionStage<List<Tweet>>
     */
	public CompletionStage<List<Tweet>> getTweet() {
		return tweet;
	}
	
	/**
     * Sets list of tweets.
     *
     * @param CompletionStage<List<Tweet>> of type tweet
     */
	public void setTweet(CompletionStage<List<Tweet>> tweet) {
		this.tweet = tweet;
	}
}
