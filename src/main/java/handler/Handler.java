package handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * Handler class
 */
public final class Handler implements RequestHandler<String, String> {

    @Override
    public String handleRequest(final String s, final Context context) {
        return "Hello World";
    }
}
