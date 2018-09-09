package modelTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

import models.DataProcessingOnSearchTweets;
import service.MockTwitterApi;
import service.TwitterApi;
import service.MockTwitterApi.mockHashTagEntiy;
import service.MockTwitterApi.mockStatus;
import service.MockTwitterApi.mockUser;
import twitter4j.GeoLocation;
import twitter4j.HashtagEntity;
import twitter4j.Query;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.User;

/**
 * Performs JUnit test cases for models.DataProcessingOnSerchTweetsModel class.
 */
public class TestDataProcessingOnSerchTweetsModel {
	
	
	/**
	 * Implements Tests for testSearchHashTag method.
	 */
	@Test
	public void testSearchHashTag() throws InterruptedException, ExecutionException {
		TwitterApi twitterService= new MockTwitterApi();
		List<Status> expected=new ArrayList<Status>(); 
		IntStream.range(0, 10).forEach(i->{Status s= new mockStatus("username "+i, "Mock Tweet for testing"+i , "MockLocation"+i, Arrays.asList("testHashtag1","testHashtag2","testHashtag3"), 45.516136, 73.656830); expected.add(s);});
		List<Status> actual = DataProcessingOnSearchTweets.searchHashTag("Hello", twitterService).get();
		assertThat(actual.size()).isEqualTo(expected.size());
		IntStream.range(0, expected.size()-1).forEach(i->assertThat(actual.get(i).getText()).isEqualTo(expected.get(i).getText()));
		
	}
	
	/**
	 * Implements Tests for getUser20TopTweets method.
	 */
	@Test
	public void testgetUser20TopTweets() throws  TwitterException, InterruptedException, ExecutionException {
		TwitterApi twitterService= new MockTwitterApi();
		
		List<Status> expected=new ArrayList<Status>(); 
		IntStream.range(0, 10).forEach(i->{Status s= new mockStatus("username "+i, "Mock Tweet for testing"+i , "MockLocation"+i, Arrays.asList("testHashtag1","testHashtag2","testHashtag3"), 45.516136, 73.656830); expected.add(s);}); 
		List<Status> actual = DataProcessingOnSearchTweets.getUser20TopTweets("Test", twitterService).get();
		assertThat(actual.size()).isEqualTo(expected.size());
		IntStream.range(0, expected.size()-1).forEach(i->assertThat(actual.get(i).getText()).isEqualTo(expected.get(i).getText()));
		
	}
	
	/**
	 * Implements Tests for getUserData method.
	 */
	@Test
	public void testgetUserData() throws  TwitterException, InterruptedException, ExecutionException {
		TwitterApi twitterService= new MockTwitterApi();
		
		User expected= new mockUser("UserFOrTesting"); 
		User actual = DataProcessingOnSearchTweets.getUserData("Test", twitterService).get();
		assertThat(actual.getScreenName()).isEqualTo(expected.getScreenName());
		
	}
	
	/**
	 * Implements Tests for searchLocationTweets method.
	 */
	
	@Test
	public void testsearchLocationTweets() throws  TwitterException, InterruptedException, ExecutionException {
		TwitterApi twitterService= new MockTwitterApi();
		List<Status> expected=new ArrayList<Status>(); 
		IntStream.range(0, 10).forEach(i->{Status s= new mockStatus("username "+i, "Mock Tweet for testing"+i , "MockLocation"+i, Arrays.asList("testHashtag1","testHashtag2","testHashtag3"), 45.516136, 73.656830); expected.add(s);});
		List<String> actual = DataProcessingOnSearchTweets.searchLocationTweets(10.0, 10.0, twitterService).get();
		assertThat(actual.size()).isEqualTo(expected.size());
		IntStream.range(0, expected.size()-1).forEach(i->assertThat(actual.get(i)).isEqualTo(expected.get(i).getText()));
		
	}
	
	/**
	 * Implements Tests for setEmotionCountHappy method.
	 */
	@Test
	public void testsetEmotionCountHappy() throws  TwitterException, InterruptedException, ExecutionException {
		List<Status> l= new ArrayList<>();
		IntStream.range(0, 100).forEach(i->{Status s= new mockStatus("username "+i, "Mock Tweet for :-) testing"+i , "MockLocation"+i, Arrays.asList("testHashtag1","testHashtag2","testHashtag3"), 45.516136, 73.656830); l.add(s);});
		assertThat(DataProcessingOnSearchTweets.setEmotionCount(l)).isEqualTo(":-)");
		
	}
	
	/**
	 * Implements Tests for setEmotionCountSad method.
	 */
	@Test
	public void testsetEmotionCountSad() throws  TwitterException, InterruptedException, ExecutionException {
		List<Status> l= new ArrayList<>();
		IntStream.range(0, 100).forEach(i->{Status s= new mockStatus("username "+i, "Mock Tweet for :-( testing"+i , "MockLocation"+i, Arrays.asList("testHashtag1","testHashtag2","testHashtag3"), 45.516136, 73.656830); l.add(s);});
		assertThat(DataProcessingOnSearchTweets.setEmotionCount(l)).isEqualTo(":-(");
		
	}
	
	/**
	 * Implements Tests for setEmotionCountneutral method.
	 */
	@Test
	public void testsetEmotionCountneutral() {
		List<Status> l= new ArrayList<>();
		IntStream.range(0, 100).forEach(i->{Status s= new mockStatus("username "+i, "Mock Tweet for testing"+i , "MockLocation"+i, Arrays.asList("testHashtag1","testHashtag2","testHashtag3"), 45.516136, 73.656830); l.add(s);});
		assertThat(DataProcessingOnSearchTweets.setEmotionCount(l)).isEqualTo(":-|");
		
	}
	
	
	/**
	 * Implements Tests for setEmpty method.
	 */
	@Test
	public void testsetEmpty() {
		DataProcessingOnSearchTweets a = new DataProcessingOnSearchTweets();
		List<Status> l= new ArrayList<>();
		//IntStream.range(0, 100).forEach(i->{Status s= new mockStatus("username "+i, "Mock Tweet for testing"+i , "MockLocation"+i, Arrays.asList("testHashtag1","testHashtag2","testHashtag3"), 45.516136, 73.656830); l.add(s);});
		assertThat(DataProcessingOnSearchTweets.setEmotionCount(l)).isEqualTo(":-|");
		
	}
	
	/**
	 * Implements Tests for createWordCount method.
	 */
	@Test
	public void testcreateWordCount() throws  TwitterException, InterruptedException, ExecutionException {
		TwitterApi twitterService= new MockTwitterApi();
		List<String> l= new ArrayList<>();
		
		LinkedHashMap<String,Long> hm= DataProcessingOnSearchTweets.createWordCount("test", twitterService).get();
		assertThat(hm.get("tweet")).isEqualTo(10);
	}
	
}
