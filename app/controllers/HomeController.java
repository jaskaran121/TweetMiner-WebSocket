package controllers;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import javax.inject.Inject;
import java.util.LinkedHashMap;
import actors.ReactiveTweetActor;
import actors.TweetUserActor;
import actors.UserActor;
import actors.HashTagActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import org.slf4j.Logger;
import static akka.pattern.Patterns.ask;
import com.fasterxml.jackson.databind.JsonNode;
import actors.UserActor;
import akka.stream.Materializer;
import play.libs.streams.ActorFlow;
import play.mvc.Controller;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;
import play.mvc.WebSocket;
import scala.compat.java8.FutureConverters;
import service.ActualTwitterApi;
import service.TwitterApi;
import twitter4j.Query;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.User;
import akka.stream.javadsl.Flow;
import play.mvc.*;
import play.api.libs.json.Json;
import play.libs.F.Either;
import akka.NotUsed;
import java.util.Map;
import actors.WordCountActor;
import actors.LocationActor;

/**
 * This HomeController program enables opening a Websocket
 * with origin check to handle requests for searching tweets
 * as per catchphrases.
 * 
 * @author Jaskaran Singh
 * @author Gagandeep Singh
 */
public class HomeController extends Controller {
	private ActorSystem actorSystem;
	private Materializer materializer;
	private ActorRef reactiveTweetActor;
	private TwitterApi twitterService ;
	private ActorRef tweetUserActor;
	private ActorRef hashTagActor;
	private ActorRef wordCountActor;
	private ActorRef locationActor;
	 private final Logger logger = org.slf4j.LoggerFactory.getLogger("controllers.HomeController");
	 
	 
	 /**
	 * Creates a Responsive Home Controller
	 *
	 * Creates actorsRef for reactiveTweetActor,tweetUserActor,
	 * hashTagActor,wordCountActor,locationActor in
	 * actorSystem.
	 * 
	 * @param system of type ActorSystem
	 * @param materializer of type Materializer
	 * @param twitterService of type twitterService
	 */
	@Inject
	public HomeController(ActorSystem system,Materializer materializer,TwitterApi twitterService ) {
		this.actorSystem = system;
		this.materializer = materializer;
		this.twitterService=twitterService;
		this.reactiveTweetActor = actorSystem.actorOf(ReactiveTweetActor.getProps(this.twitterService),"reactiveTweetActor");
		this.tweetUserActor=actorSystem.actorOf(TweetUserActor.getProps(this.twitterService),"tweetUserActor");
		this.hashTagActor=actorSystem.actorOf(HashTagActor.getProps(this.twitterService),"hashtagActor");
		this.wordCountActor = actorSystem.actorOf(WordCountActor.getProps(this.twitterService),"wordCountActor");
		this.locationActor = actorSystem.actorOf(LocationActor.getProps(this.twitterService),"locationActor");
	}
	
	 /**
     * Renders index page
     * 
     */
      public Result index() {
        return ok(views.html.index.render(request()));
    }
      
      /**
       * Creates WebSocket.
       * 
       * The request origin parameter is been verified.
       *
       * @return http request upgraded to websocket protocol.
       */
    public WebSocket ws() {
    	 return WebSocket.Json.acceptOrResult(request -> {
             if (sameOriginCheck(request)) {     
            	 
            	 return CompletableFuture.completedFuture(
                        Either.Right(ActorFlow.actorRef(UserActor::getProps,
                                 actorSystem, materializer)));
            	 

             } else {
                 return CompletableFuture.completedFuture(Either.Left(forbidden()));
             }
         });
    	//return WebSocket.Json.accept(request -> ActorFlow.actorRef(UserActor::getProps,actorSystem,materializer));
    }
    
    /** 
     * Checks that the WebSocket address matches the 
     * Http Request header.
     *
     * @param origin http request origin field
     * @return origin check of type boolean
     */
    public boolean sameOriginCheck(Http.RequestHeader rh) {
        final String origin = rh.header("Origin").orElse("");

     if (origin.contains("localhost:9000") || origin.contains("localhost:19001")) {
            logger.debug("originValue = " + origin);
            return true;
        } else {
            logger.error("originCheck: rejecting request because Origin header value " + origin + " is not in the same origin");
            return false;
        }
    }
    
    
    /**
     * Renders user page
     *
     * Asks TweetUserActor for user related data.
     * 
     * @param  screenName of user of type String
     * @return CompletableFuture of a result with a rendered user page
     * @author Gagan
     */
	public CompletableFuture<Result> showUser(String screenName) 
	{
		return (CompletableFuture<Result>) FutureConverters.toJava(ask(tweetUserActor, new TweetUserActor.TweetUserActorProtocolGetUser(screenName) , 10000))
		.thenCombine(FutureConverters.toJava(ask(tweetUserActor, new TweetUserActor.TweetUserActorProtocolGetTimeLine(screenName) , 20000)),	(user,recentPost)-> ok(views.html.user.render((CompletableFuture<User>) user, (CompletableFuture<List<Status>>) recentPost)));

		
	}
	
	/**
     * Renders hashtag page.
     *
     * Asks HashTagActor for tweets fetched by hashtag.
     * 
     * @param  searchKeyword of type String
     * @return CompletableFuture of a result with a rendered hashtag page
     * @author Jaskaran
     */
	public CompletionStage<Result> searchHashTag(String searchKeyword)
	{
		return (CompletionStage<Result>) FutureConverters.toJava(ask(hashTagActor,new HashTagActor.HashtagActorProtocol(searchKeyword) , 10000))
				.thenApply(tweets-> ok(views.html.hashtag.render((CompletableFuture<List<Status>>) tweets,searchKeyword)));
	
	}
	
	/**
     * Renders wordcount page.
     *
     * Asks WordCountActor for word versus count table.
     * 
     * @param  keyword searched type String
     * @return CompletableFuture of a result with a rendered wordcount page
     * @author Gurdip
     */
	public CompletionStage<Result> createWordCount(String keyword)
	{
		return (CompletionStage<Result>) FutureConverters.toJava(ask(wordCountActor,new WordCountActor.WordCountActorProtocol(keyword),10000)).
				thenApply(hmap->ok(views.html.wordcount.render((CompletableFuture<LinkedHashMap<String,Long>>) hmap)));
	}

	/**
     * Renders location page.
     *
     * Asks LocationActor for tweets fetched by location.
     * 
     * @param  searchKeyword of type String
     * @return CompletableFuture of a result with a rendered location page
     * @author Jaskaran
     */
	public CompletionStage<Result> getLocation(Double latitude,Double longitude)
	{
		return (CompletionStage<Result>) FutureConverters.toJava(ask(locationActor,new LocationActor.LocationActorProtocol(latitude,longitude),10000)).
				thenApply(list->ok(views.html.location.render((CompletableFuture<List<String>>) list,latitude,longitude)));
	}

}
