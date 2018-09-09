package actors;

import java.util.List;

import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.LoggingAdapter;
import models.SearchResults;
import models.Tweet;
import play.libs.Json;
import akka.event.Logging;

/**
 * This UserActor communicates with HomeController & 
 * handles JSON Nodes.
 * 
 * @author Jaskaran Singh
 * @author Gagandeep Singh
 */
public class UserActor extends AbstractActor {

	private final ActorRef ws;
	private final LoggingAdapter logger = Logging.getLogger(getContext().system(), this);
	
	/**
	 * Parameterized Constructor.
	 * 
	 * @param wsOut of type ActorRef
	 */
	public UserActor(final ActorRef wsOut) {
		ws = wsOut;
	}
	/**
     * Forms props to create UserActor.
     * 
     * @param  wsOut of type ActorRef
     * @return Newly created props
     */
	public static Props getProps(final ActorRef wsout) {
		return Props.create(UserActor.class, wsout);
	}
	
	@Override
	public void preStart() {
		
	}
	
	/**
     * This is used to store result instance of type SearchResults.
     */
	public static class TweetDataMessage { 
        public final SearchResults result;
        public TweetDataMessage(SearchResults result) {
            this.result = result;
        }
	}
	
	/**
     * This is used to form outMessage instance containing keyword searched,
     * list of tweets fetched & sentiment parameter.
     */
	public static class outMessage { 
        public  String keyword;
        public List<Tweet> tweet;
		public String emotion;
		
		public outMessage(String keyword, List<Tweet> tweet,String emotion) {
			
			this.keyword = keyword;
			this.tweet = tweet;
			this.emotion = emotion;
		}
        
	}
	
	/**
     * This sends message containing tweet data in the 
     * form of JSON node.
     * 
     * @param msg containing tweet parameters of type TweetDataMessage
     */
	 private void sendTweetData(TweetDataMessage msg) throws JsonProcessingException, InterruptedException, ExecutionException{
	        final ObjectNode response = Json.newObject();
	        ObjectMapper objectMapper = new ObjectMapper();	
	        msg.result.getEmotion().thenCombine(msg.result.getTweet(), (emotion,tw)->new outMessage(msg.result.getKeyword(), tw, emotion)).thenAccept(om->{
	        	try {
	        		
	        response.put("data",objectMapper.writeValueAsString(om));
	        ws.tell(response, self());}
	        catch(JsonProcessingException e) {}	
	        });

	    }
	
	 /**
	  * Handles JSON objects & websocket connection.
	  * 
	  */
	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(TweetDataMessage.class, msg -> {logger.info("User Actor Started"); sendTweetData(msg); })
				.match(ObjectNode.class, json -> registerNewKeyWord(json))
				.build();
				
	}
	
	/**
	  * This registers new search keyword with ReactiveTweetActor .
	  * 
	  */
	private void registerNewKeyWord(ObjectNode json) {
		context().actorSelection("/user/reactiveTweetActor/")
        	.tell(new ReactiveTweetActor.RegisterSearchKeyword(json.get("keyword").asText()), self());
	}
}
