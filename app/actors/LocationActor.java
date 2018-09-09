package actors;

import java.util.List;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.actor.AbstractActor.Receive;
import service.TwitterApi;
import models.*;

/**
 * Actor for retrieving tweets based on location.
 * 
 * @author Amankirat
 */
public class LocationActor extends AbstractActor {

TwitterApi twitterService;
	
/**
 * Parameterized Constructor
 * 
 * @param  twitterService a instance of type TwitterApi 
 */

	public LocationActor(TwitterApi twitterService) {
		
		this.twitterService = twitterService;
	}
	
	/**
     * Forms props to create LocationActor.
     * 
     * @param  twitterService a instance of type TwitterApi 
     * @return Newly created props
     */
	public static Props getProps(TwitterApi twitterService) {
		return Props.create(LocationActor.class,twitterService);
	}
	
	/**
     * This class is used to store longitudes & latitudes of 
     * location, to be searched for fetching tweets based 
     * on location.
     */
	 public static class LocationActorProtocol {
	        public final Double longitude;
	        public final Double latitude;

	        public LocationActorProtocol(Double latitude,Double longitude) {
	            this.longitude = longitude;
	            this.latitude = latitude;
	        }
	    }
	
	 
	    /**
	     * Handles Location search messages.
	     * 
	     * This matches the received message with 
	     * LocationActorProtocol instance.
	     * 
	     * Sends message of <List<String> of type CompletableFuture to
	     * home controller.
	     */
	 @Override
		public Receive createReceive() {
			// TODO Auto-generated method stub
			return receiveBuilder()
					.match(LocationActorProtocol.class, i -> 
						sender().tell(DataProcessingOnSearchTweets.searchLocationTweets(i.latitude,i.longitude,twitterService), self()))
						.build();
		}
}
