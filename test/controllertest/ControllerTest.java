package controllertest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.running;
import static play.test.Helpers.testServer;

import java.util.Collections;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;

import akka.actor.ActorSystem;
import akka.stream.Materializer;
import controllers.HomeController;
import play.mvc.Http;
import play.mvc.Result;
import play.shaded.ahc.org.asynchttpclient.AsyncHttpClient;
import play.shaded.ahc.org.asynchttpclient.AsyncHttpClientConfig;
import play.shaded.ahc.org.asynchttpclient.DefaultAsyncHttpClient;
import play.shaded.ahc.org.asynchttpclient.DefaultAsyncHttpClientConfig;
import play.shaded.ahc.org.asynchttpclient.ws.WebSocket;
import play.shaded.ahc.org.asynchttpclient.ws.WebSocketTextListener;
import play.test.Helpers;
import play.test.TestServer;
import play.test.WithServer;
import service.MockTwitterApi;
import service.TwitterApi;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.libs.Json;

import static play.inject.Bindings.bind;
public class ControllerTest extends WithServer{
       private final Materializer materializer = mock(Materializer.class);
    private ActorSystem actorSystem;
   
    	    
	private TwitterApi twitterService= new MockTwitterApi();
    
    @Test
    public void testIndexController() throws InterruptedException, ExecutionException {
    	actorSystem=ActorSystem.create();
        HomeController homeController = new HomeController(actorSystem,materializer,twitterService);

        String serverURL = "ws://localhost:19001/";
        Http.RequestBuilder request = fakeRequest("GET", serverURL);
        Http.Context context = Helpers.httpContext(request.build());
        Http.Context.current.set(context);
        Result result = homeController.index();	
        Http.Context.current.remove();
        assertThat(result.status(), is(equalTo(OK)));
        assertThat(result.contentType().get(), is(equalTo("text/html")));
        assertThat(contentAsString(result).contains("Tweet Miner"), is(equalTo(true)));
    }

    @Test
    public void testSearchHashTagController() throws InterruptedException, ExecutionException {
    	actorSystem=ActorSystem.create();
        HomeController homeController = new HomeController(actorSystem,materializer,twitterService);

        String serverURL = "ws://localhost:19001/";
        Http.RequestBuilder request = fakeRequest("GET", serverURL);
        Http.Context context = Helpers.httpContext(request.build());
        Http.Context.current.set(context);
        Result result = homeController.searchHashTag("test").toCompletableFuture().get();
        Http.Context.current.remove();


        assertThat(result.status(), is(equalTo(OK)));
        assertThat(result.contentType().get(), is(equalTo("text/html")));
        assertThat(contentAsString(result).contains("Results searched for the selected hashtag"), is(equalTo(true)));
    }
    
    @Test
    public void testcreateWordCountController() throws InterruptedException, ExecutionException {
    	actorSystem=ActorSystem.create();
        HomeController homeController = new HomeController(actorSystem,materializer,twitterService);

        String serverURL = "ws://localhost:19001/";
        Http.RequestBuilder request = fakeRequest("GET", serverURL);
        Http.Context context = Helpers.httpContext(request.build());
        Http.Context.current.set(context);
        Result result = homeController.createWordCount("test").toCompletableFuture().get();
        Http.Context.current.remove();


        assertThat(result.status(), is(equalTo(OK)));
        assertThat(result.contentType().get(), is(equalTo("text/html")));
        assertThat(contentAsString(result).contains("Word Level Statistics Table"), is(equalTo(true)));
    }
    
    @Test
    public void testshowUserController() throws InterruptedException, ExecutionException {
    	actorSystem=ActorSystem.create();
        HomeController homeController = new HomeController(actorSystem,materializer,twitterService);

        String serverURL = "ws://localhost:19001/";
        Http.RequestBuilder request = fakeRequest("GET", serverURL);
        Http.Context context = Helpers.httpContext(request.build());
        Http.Context.current.set(context);
        Result result = homeController.showUser("test").toCompletableFuture().get();
        Http.Context.current.remove();


        assertThat(result.status(), is(equalTo(OK)));
        assertThat(result.contentType().get(), is(equalTo("text/html")));
        assertThat(contentAsString(result).contains("User Profile"), is(equalTo(true)));
    }
    
    @Test
    public void testgetLocationrController() throws InterruptedException, ExecutionException {
    	actorSystem=ActorSystem.create();
        HomeController homeController = new HomeController(actorSystem,materializer,twitterService);

        String serverURL = "ws://localhost:19001/";
        Http.RequestBuilder request = fakeRequest("GET", serverURL);
        Http.Context context = Helpers.httpContext(request.build());
        Http.Context.current.set(context);
        Result result = homeController.getLocation(73.656830, 45.516136).toCompletableFuture().get();
        Http.Context.current.remove();


        assertThat(result.status(), is(equalTo(OK)));
        assertThat(result.contentType().get(), is(equalTo("text/html")));
        assertThat(contentAsString(result).contains("Results searched for the selected location"), is(equalTo(true)));
    }
    
//    @Test
//    public void testWebSocketSucess() throws InterruptedException, ExecutionException {
//    	TestServer server = testServer(19001);
//        running(server, () -> {
//            try {
//                AsyncHttpClientConfig config = new DefaultAsyncHttpClientConfig.Builder().setMaxRequestRetry(0).build();
//                AsyncHttpClient client = new DefaultAsyncHttpClient(config);
//                TestWebSocket webSocketClient = new TestWebSocket(client);
//
//                try {
//                    String serverURL = "ws://localhost:19001/ws";
//                    ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
//                    TestWebSocket.LoggingListener listener = new TestWebSocket.LoggingListener((message) -> {
//                        try {
//                            queue.put(message);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    });
//                    CompletableFuture<WebSocket> completionStage = webSocketClient.call(serverURL, listener);
//
//                    await().until(completionStage::isDone);
//                    WebSocket websocket = completionStage.get();
//                    await().until(() -> websocket.isOpen() && queue.peek() != null);
//                    String input = queue.take();
//
//                    JsonNode json = Json.parse(input);
//                    String symbol = json.get("keyword").asText();
//                    assertThat(symbol).isEqualTo("Hello");
//                } finally {
//                    client.close();
//                }
//            } catch (Exception e) {
//                fail("Unexpected exception", e);
//            }
//        });
//    }
    @Test
    public void testWebSocketreject() throws InterruptedException, ExecutionException {
    	TestServer server = testServer(37117);
        running(server, () -> {
            try {
                AsyncHttpClientConfig config = new DefaultAsyncHttpClientConfig.Builder().setMaxRequestRetry(0).build();
                AsyncHttpClient client = new DefaultAsyncHttpClient(config);
                TestWebSocket webSocketClient = new TestWebSocket(client);

                try {
                    String serverURL = "ws://localhost:37117/ws";
                    
                    CompletableFuture<WebSocket> completionStage = webSocketClient.call(serverURL);
                    await().until(completionStage::isDone);
                    assertThat(completionStage)
                            .hasFailedWithThrowableThat()
                            .hasMessageContaining("Invalid Status Code 403");
                } finally {
                    client.close();
                }
            } catch (Exception e) {
                fail("Unexpected exception", e);
            }
        });
    }

    @Test
    public void testWebSocketsucess() throws InterruptedException, ExecutionException {
    	TestServer server = testServer(19001);
        running(server, () -> {
            try {
                AsyncHttpClientConfig config = new DefaultAsyncHttpClientConfig.Builder().setMaxRequestRetry(0).build();
                AsyncHttpClient client = new DefaultAsyncHttpClient(config);
                TestWebSocket webSocketClient = new TestWebSocket(client);

                try {
                    String serverURL = "ws://localhost:19001/ws";
                    
                    CompletableFuture<WebSocket> completionStage = webSocketClient.call(serverURL);
                    await().until(completionStage::isDone);
                    assertThat(true, is(equalTo(completionStage.get().isOpen())));
                } finally {
                    client.close();
                }
            } catch (Exception e) {
                fail("Unexpected exception", e);
            }
        });
        
    	server = testServer(9000);
        running(server, () -> {
            try {
                AsyncHttpClientConfig config = new DefaultAsyncHttpClientConfig.Builder().setMaxRequestRetry(0).build();
                AsyncHttpClient client = new DefaultAsyncHttpClient(config);
                TestWebSocket webSocketClient = new TestWebSocket(client);

                try {
                    String serverURL = "ws://localhost:9000/ws";
                    
                    CompletableFuture<WebSocket> completionStage = webSocketClient.call(serverURL);
                    await().until(completionStage::isDone);
                    assertThat(true, is(equalTo(completionStage.get().isOpen())));
                } finally {
                    client.close();
                }
            } catch (Exception e) {
                fail("Unexpected exception", e);
            }
        });
    }
    
    
    

}
