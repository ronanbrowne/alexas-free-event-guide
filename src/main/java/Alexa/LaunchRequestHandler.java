package Alexa;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;

public class LaunchRequestHandler implements RequestHandler {




    /**
     * The canHandle method returns true if the incoming request is a LaunchRequest
     * @param input
     * @return
     */
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.requestType(LaunchRequest.class));
    }



    /**
     * The handle method generates and returns a basic greeting response.
     * @param input
     * @return
     */
    @Override
    public Optional<Response> handle(HandlerInput input) {

        String speechText = "Welcome to the Alexa Skills Kit, you can say hello";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("HelloWorld", speechText)
                .withReprompt(speechText)
                .build();
    }
}