package handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.http.HttpRequest;

import org.joda.time.DateTime;

/**
 * Handler class
 */
public class Handler implements RequestStreamHandler {

    private static final String URL_TELEGRAM = "https://api.telegram.org/%s/sendMessage?chat_id=%s&text=\"%s\"";
    private static final String BOT_TOKEN = System.getenv("bot_token");
    private static final String CHAT_ID = System.getenv("chat_id");
    private static final String HALF_MONTH_MESSAGE = System.getenv("half_month_message");
    private static final String END_MONTH_MESSAGE = System.getenv("end_month_message");

    @Override
    public void handleRequest(final InputStream inputStream, final OutputStream outputStream, final Context context) throws IOException {
        final int currentDay = DateTime.now().dayOfMonth().get();

        final String finalUrl;

        if (currentDay == 15) {
            finalUrl = String.format(URL_TELEGRAM, "bot"+ BOT_TOKEN, CHAT_ID, HALF_MONTH_MESSAGE);
        } else {
            finalUrl = String.format(URL_TELEGRAM, "bot"+ BOT_TOKEN, CHAT_ID, END_MONTH_MESSAGE);
        }

        HttpRequest.newBuilder()
                .uri(URI.create(finalUrl))
                .GET()
                .build();
    }
}
