package ActorsTest;
import akka.testkit.javadsl.TestKit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

import service.MockTwitterApi;
import service.TwitterApi;
import service.MockTwitterApi.mockStatus;
import service.MockTwitterApi.mockUser;
import twitter4j.Status;
import twitter4j.User;
import models.*;

import actors.*;

public class TweetUserActorTest {
	
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
        	 final ActorRef user = system.actorOf(TweetUserActor.getProps(t));
        	 
        	 user.tell(new TweetUserActor.TweetUserActorProtocolGetUser("abc"),  testProbe.getRef());
        	
        	 User expected= new mockUser("UserFOrTesting"); 
  
        	 CompletableFuture<User> l= testProbe.expectMsgClass(CompletableFuture.class);
        	 
        	 User actual= l.get();
        	 assertThat(actual.getScreenName()).isEqualTo(expected.getScreenName());
        	 
        	 
        	 user.tell(new TweetUserActor.TweetUserActorProtocolGetTimeLine("abc"),  testProbe.getRef());
        	 List<Status> expected1=new ArrayList<Status>();
        	 IntStream.range(0, 10).forEach(i->{Status s= new mockStatus("username "+i, "Mock Tweet for testing"+i , "MockLocation"+i, Arrays.asList("testHashtag1","testHashtag2","testHashtag3"), 45.516136, 73.656830); expected1.add(s);}); 
        	 CompletableFuture<List<Status>> l1 = testProbe.expectMsgClass(CompletableFuture.class);
        	 List<Status> actual1= l1.get();
        	 assertThat(actual1.size()).isEqualTo(expected1.size());
     		IntStream.range(0, expected1.size()-1).forEach(i->assertThat(actual1.get(i).getText()).isEqualTo(expected1.get(i).getText()));
        }};}
}
