package handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import org.joda.time.DateTime;

/**
 * Handler class
 */
public class Handler implements RequestStreamHandler {

    private static final String URL_TELEGRAM = "https://api.telegram.org/%s/sendMessage?chat_id=%s&text='%s'";
    private static final String BOT_TOKEN = System.getenv("bot_token");
    private static final String CHAT_ID = System.getenv("chat_id");
    private static final String HALF_MONTH_MESSAGE = System.getenv("half_month_message");
    private static final String END_MONTH_MESSAGE = System.getenv("end_month_message");

    @Override
    public void handleRequest(final InputStream inputStream, final OutputStream outputStream, final Context context) throws IOException {
        final int currentDay = DateTime.now().dayOfMonth().get();

        final String message;
        if (currentDay == 15) {
            message = HALF_MONTH_MESSAGE;
        } else {
            message = END_MONTH_MESSAGE;
        }

        // URL-encode the message text
        final String encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8);

        // Construct the final URL
        final String finalUrl = String.format(URL_TELEGRAM, "bot" + BOT_TOKEN, CHAT_ID, encodedMessage);
        System.out.println(finalUrl);
        // Build the HTTP request
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(finalUrl))
                .GET()
                .build();

        // Create an HttpClient instance
        final HttpClient client = HttpClient.newHttpClient();

        // Send the request and get the response
        try {
            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response code: " + response.statusCode());
        } catch (final InterruptedException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}