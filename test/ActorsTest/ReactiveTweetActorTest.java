package ActorsTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import actors.ReactiveTweetActor;
import actors.UserActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.javadsl.TestKit;
import models.Tweet;
import service.MockTwitterApi;
import service.MockTwitterApi.mockStatus;
import service.TwitterApi;
import twitter4j.Status;


public class ReactiveTweetActorTest {

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
        	 final ActorRef reactiveTweetActor = system.actorOf(ReactiveTweetActor.getProps(t));
        	 reactiveTweetActor.tell(new ReactiveTweetActor.RegisterSearchKeyword("test"),  testProbe.getRef());
        	 reactiveTweetActor.tell(new ReactiveTweetActor.Tick(),  ActorRef.noSender());
        	 List<Status> expected=new ArrayList<Status>(); 
        	 IntStream.range(0, 10).forEach(i->{Status s= new mockStatus("username "+i, "Mock Tweet for testing"+i , "MockLocation"+i, Arrays.asList("testHashtag1","testHashtag2","testHashtag3"), 45.516136, 73.656830); expected.add(s);});
        	 UserActor.TweetDataMessage msg= testProbe.expectMsgClass(UserActor.TweetDataMessage.class);
        	 assertThat(msg.result.getKeyword()).isEqualTo("test");
        	 List<Tweet> actual= msg.result.getTweet().toCompletableFuture().get();
        	 
        	 assertThat(actual.size()).isEqualTo(expected.size());
     		IntStream.range(0, expected.size()-1).forEach(i->assertThat(actual.get(i).getTweet()).isEqualTo(expected.get(i).getText()));
        }};}

}

