package controllertest;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

import org.slf4j.Logger;
import play.shaded.ahc.org.asynchttpclient.AsyncHttpClient;
import play.shaded.ahc.org.asynchttpclient.BoundRequestBuilder;
import play.shaded.ahc.org.asynchttpclient.ListenableFuture;
import play.shaded.ahc.org.asynchttpclient.ws.WebSocket;
import play.shaded.ahc.org.asynchttpclient.ws.WebSocketTextListener;
import play.shaded.ahc.org.asynchttpclient.ws.WebSocketUpgradeHandler;

public class TestWebSocket {
	private AsyncHttpClient client;

    public TestWebSocket(AsyncHttpClient c) {
        this.client = c;
    }

    public CompletableFuture<WebSocket> call(String url) throws ExecutionException, InterruptedException {
        final BoundRequestBuilder requestBuilder = client.prepareGet(url);

        final WebSocketUpgradeHandler handler = new WebSocketUpgradeHandler.Builder().build();
        final ListenableFuture<WebSocket> future = requestBuilder.execute(handler);
        return future.toCompletableFuture();
    }

    



}
