package Alexa;

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import com.amazon.ask.SkillStreamHandler;


public class HelloWorldStreamHandler extends SkillStreamHandler {

    /**
     * method creates an SDK instance using the Skills.standard builder.
     * We create instances of our request handlers and register them with our skill with the addRequestHandlers builder method.
     * The Alexa.HelloWorldStreamHandler constructor passes the constructed Skill instance to the constructor for the superclass SkillStreamHandler.
     * @return
     */
    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(new CancelandStopIntentHandler(),
                        new HelloWorldIntentHandler(),
                        new LaunchRequestHandler())
                .withSkillId("amzn1.ask.skill.955002f3-cf32-4623-9e36-033a3c90fdce")
                .build();
    }



    public HelloWorldStreamHandler() {
        super(getSkill());
    }
}
