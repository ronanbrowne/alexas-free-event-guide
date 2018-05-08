import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import com.amazon.ask.SkillStreamHandler;


public class HelloWorldStreamHandler extends SkillStreamHandler {

    /**
     * method creates an SDK instance using the Skills.standard builder.
     * We create instances of our request handlers and register them with our skill with the addRequestHandlers builder method.
     * The HelloWorldStreamHandler constructor passes the constructed Skill instance to the constructor for the superclass SkillStreamHandler.
     * @return
     */
    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(new CancelandStopIntentHandler(), new HelloWorldIntentHandler(), new LaunchRequestHandler())
                .build();
    }



    public HelloWorldStreamHandler(Skill skill) {
        super(skill);
    }
}
