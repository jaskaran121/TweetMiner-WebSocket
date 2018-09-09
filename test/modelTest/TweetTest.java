package modelTest;
import java.util.*;

import service.MockTwitterApi;
import service.MockTwitterApi.mockHashTagEntiy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.Test;
import models.Tweet;
import twitter4j.HashtagEntity;
/**
 * Performs JUnit test cases for models.Tweet class.
 */
public class TweetTest {
	
	
	Tweet t = new Tweet();
	
	/**
	 * Implements Tests for getScreenName & setScreenName methods.
	 */
	@Test
	public void testSetAndGetScreenName() {
		t.setScreenName("samsung");
		assertThat(t.getScreenName()).isEqualTo("samsung");
	}
	
	/**
	 * Implements Tests for getTweet & setTweet methods.
	 */
	@Test
	public void testSetAndGetTweet() {
		t.setTweet("Today wheather is good");
		assertThat(t.getTweet()).isEqualTo("Today wheather is good");
	}

	/**
	 * Implements Tests for getLocation & setLocation methods.
	 */
	@Test
	public void testSetAndGetLocation() {
		t.setLocation("New York");
		assertThat(t.getLocation()).isEqualTo("New York");
	}
	
	/**
	 * Implements Tests for getLatitude & setLatitude methods.
	 */
	@Test
	public void testSetAndGetLatitude() {
		t.setLatitude(45.5017);
		assertThat(t.getLatitude()).isEqualTo(45.5017);
	}
	
	/**
	 * Implements Tests for getLongitude & setLongitude methods.
	 */
	@Test
	public void testSetAndGetLongitude() {
		t.setLongitude(73.5673);;
		assertThat(t.getLongitude()).isEqualTo(73.5673);
	}
	
	/**
	 * Implements Tests for getHashtags & setHashtags methods.
	 */
	@Test
	public void testSetAndGetHashtags() {
		
		List<HashtagEntity> myList = Arrays.asList(new MockTwitterApi.mockHashTagEntiy("Apple"),new MockTwitterApi.mockHashTagEntiy("Google"));
		
		t.setHashtags(myList);
		List<String> myList1 = Arrays.asList("Apple","Google");
		assertThat(t.getHashtags()).isEqualTo(myList1);
	}
	
	/**
   	 *Implements Tests for contructor of class models.Tweet.
   	 */
	@Test
	public void testSetAndGetConstructor() {
		List<HashtagEntity> myList = Arrays.asList(new MockTwitterApi.mockHashTagEntiy("Apple"),new MockTwitterApi.mockHashTagEntiy("Google"));
		Tweet t = new Tweet("Apple","Today wheather is good","New York",73.5673,45.5017,myList);
		assertThat(t.getScreenName()).isEqualTo("Apple");
	}
}
