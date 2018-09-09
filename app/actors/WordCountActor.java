package actors;


import java.util.LinkedHashMap;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.actor.AbstractActor.Receive;
import service.TwitterApi;
import models.*;

/**
 * Actor for forming word versus count table.
 * 
 * @author Gurdip
 */
public class WordCountActor extends AbstractActor{
	TwitterApi twitterService;

	/**
	 * Parameterized Constructor
	 * 
	 * @param  twitterService a instance of type TwitterApi 
	 */
public WordCountActor(TwitterApi twitterService) {
		
		this.twitterService = twitterService;
	}


/**
 * Forms props to create WordCountActor
 * 
 * @param  twitterService a instance of type TwitterApi 
 * @return Newly created props
 */
public static Props getProps(TwitterApi twitterService) {
	return Props.create(WordCountActor.class,twitterService);
}


/**
 * This class is used to store keyword,
 * for which table is to be formed.
 */
public static class WordCountActorProtocol {
    public final String keyword;

    public  WordCountActorProtocol(String keyword) {
        this.keyword = keyword;
    }
}


/**
 * Handles creating word count messages.
 * 
 * This matches the received message with 
 * WordCountActorProtocol instance.
 * 
 * Sends message of LinkedHashMap<String,Long> of type CompletableFuture to
 * home controller.
 */
@Override
public Receive createReceive() {
	// TODO Auto-generated method stub
	return receiveBuilder()
			.match( WordCountActorProtocol.class, searchKeyword -> 
				sender().tell(DataProcessingOnSearchTweets.createWordCount(searchKeyword.keyword,twitterService), self()))
				.build();
}


}


