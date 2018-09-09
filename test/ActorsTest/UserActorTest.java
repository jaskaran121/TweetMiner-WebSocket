package ActorsTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import actors.ReactiveTweetActor;
import actors.UserActor;
import actors.UserActor.TweetDataMessage;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.javadsl.TestKit;
import models.SearchResults;
import models.Tweet;
import net.sf.ehcache.search.expression.IsNull;
import play.libs.Json;
import service.MockTwitterApi;
import service.MockTwitterApi.mockStatus;
import service.TwitterApi;
import twitter4j.Status;
import models.*;



public class UserActorTest {

	static ActorSystem system;
	static TwitterApi t;
	@BeforeClass
    public static void setup() {
        system = ActorSystem.create();
        t = new MockTwitterApi();
    }
	
	@AfterClass
    public static void teardown() {
        TestKit.shutdownActorSystem(system);
        system = null;
    }

	@Test
    public void testIt() throws InterruptedException, ExecutionException{
        new TestKit(system) {{
        	 final TestKit testProbe = new TestKit(system);
        	 final TestKit testProbe1 = new TestKit(system);
        	 final ActorRef userActor = system.actorOf(UserActor.getProps(testProbe.getRef()));
        	 final ObjectNode request = Json.newObject();
 	       request.put("keyword","Hello"); 
 	        testProbe1.getRef().tell(request, ActorRef.noSender());
 	        
 	       final ActorRef reactiveTweetActor = system.actorOf(ReactiveTweetActor.getProps(t));
 	      
 	        SearchResults result= new SearchResults();
 	       TweetDataMessage msg= new TweetDataMessage(result);
 	       UserActor.outMessage om= new UserActor.outMessage("Hello", Arrays.asList(new Tweet(),new Tweet(),new Tweet()), ":-|");
 	      testProbe1.getRef().tell(msg, ActorRef.noSender());
 	       //ObjectNode response=testProbe.expectMsgClass(ObjectNode.class);
 	     // Assert.assertEquals(testProbe.getRef(), userActor.getLastSender());
 	    
        }};}

}

