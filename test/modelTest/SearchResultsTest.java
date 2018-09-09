package modelTest;
import models.Tweet;
import service.MockTwitterApi;
import twitter4j.HashtagEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.junit.Test;
import models.SearchResults;

/**
 * Performs JUnit test cases for models.SearchResults class.
 */
public class SearchResultsTest {
	SearchResults s = new SearchResults();
	
	/**
	 * Implements Tests for getKeyword & setKeyword methods.
	 */
    @Test
    public void testSetAndGetKeyword() {
         s.setKeyword("apple");
         assertThat(s.getKeyword()).isEqualTo("apple");
    }

    /**
	 *Implements Tests for getEmotion & setEmotion methods.
	 */
    @Test
    public void testSetAndGetEmotion() {
    	CompletionStage<String> emotion = CompletableFuture.completedFuture("Yahoo");
         s.setEmotion(emotion);
         assertThat(s.getEmotion()).isEqualTo(emotion);
    }
    
    /**
   	 *Implements Tests for getTweet & setTweet methods.
   	 */
    @Test
    public void testSetAndGetTweet() {
    	List<HashtagEntity> myList = Arrays.asList(new MockTwitterApi.mockHashTagEntiy("Apple"),new MockTwitterApi.mockHashTagEntiy("Google"));
          List<Tweet> myList1 = Arrays.asList(new Tweet("Apple","Today wheather is good","New York",73.5673,45.5017,myList));    	
          
    	CompletionStage<List<Tweet>> t = CompletableFuture.completedFuture(myList1);
         s.setTweet(t);
         assertThat(s.getTweet()).isEqualTo(t);
    }

    /**
   	 *Implements Tests for contructor of class models.SearchResults.
   	 */
    @Test
	public void testSetAndGetConstructor() {
    	List<HashtagEntity> myList = Arrays.asList(new MockTwitterApi.mockHashTagEntiy("Apple"),new MockTwitterApi.mockHashTagEntiy("Google"));
        List<Tweet> myList1 = Arrays.asList(new Tweet("Apple","Today wheather is good","New York",73.5673,45.5017,myList));    	
   CompletionStage<List<Tweet>> t = CompletableFuture.completedFuture(myList1);
  	CompletionStage<String> emotion = CompletableFuture.completedFuture("Yahoo");
  	SearchResults result = new SearchResults("ABC",t,emotion);
    assertThat(result.getKeyword()).isEqualTo("ABC");
	}
	
	
}
