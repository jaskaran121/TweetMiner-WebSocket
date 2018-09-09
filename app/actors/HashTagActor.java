package actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import service.TwitterApi;
import models.*;

/**
 * Actor for retrieving tweets based on hashtag keywords.
 * 
 * @author Jaskaran
 */
public class HashTagActor extends AbstractActor {

	TwitterApi twitterService;
	
	/**
	 * Parameterized Constructor
	 * 
	 * @param  twitterService a instance of type TwitterApi 
	 */
	public HashTagActor(TwitterApi twitterService) {
		
		this.twitterService = twitterService;
	}
	
	/**
     * Forms props to create HashTagActor.
     * 
     * @param  twitterService a instance of type TwitterApi
     * @return Newly created props
     */
	public static Props getProps(TwitterApi twitterService) {
		return Props.create(HashTagActor.class,twitterService);
	}
	
	/**
     * This class is used to store name, to be 
     * searched for fetching tweets based 
     * on hashtag.
     */
    public static class HashtagActorProtocol {
        public final String name;

        public HashtagActorProtocol(String name) {
            this.name = name;
        }
    }
    
    /**
     * Handles Hashtag search messages.
     * 
     * This matches the received message with 
     * HashTagActorProtocol instance.
     * 
     * Sends message of list of type completable future to
     * home controller.
     */
	@Override
	public Receive createReceive() {
		// TODO Auto-generated method stub
		return receiveBuilder()
				.match(HashtagActorProtocol.class, searchKeyword -> 
					sender().tell(DataProcessingOnSearchTweets.searchHashTag(searchKeyword.name,twitterService), self()))
					.build();
	}

}
