package Alexa;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;

public class HelloWorldIntentHandler implements RequestHandler {

    /**
     * Method detects if the incoming request is an IntentRequest, and returns
     * @param input - incoming request
     * @return true if the intent name is HelloWorldIntent
     */
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("HelloWorldIntent"));
    }

    /**
     * Response we may get
     * @param input incoming request
     * @return the response alexa will give
     */
    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Hello you bad mother fuckers";


        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("HelloWorld", speechText)
                .build();
    }
}
