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
import twitter4j.Status;
import models.*;

import actors.*;

public class LocationActorTest {

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
        	 final ActorRef location = system.actorOf(LocationActor.getProps(t));
        	 location.tell(new LocationActor.LocationActorProtocol(2.2,1.6),  testProbe.getRef());
        	 
        	 List<Status> expected=new ArrayList<Status>(); 
        	 IntStream.range(0, 10).forEach(i->{Status s= new mockStatus("username "+i, "Mock Tweet for testing"+i , "MockLocation"+i, Arrays.asList("testHashtag1","testHashtag2","testHashtag3"), 45.516136, 73.656830); expected.add(s);});
        	 CompletableFuture<List<String>> l= testProbe.expectMsgClass(CompletableFuture.class);
        	 
        	 List<String> actual= l.get();
        	 
        	 assertThat(actual.size()).isEqualTo(expected.size());
        	 IntStream.range(0, expected.size()-1).forEach(i->assertThat(actual.get(i)).isEqualTo(expected.get(i).getText()));
        }};}
	
}
