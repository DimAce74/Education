/**
 * Created by db2admin on 12.05.2017.
 */
import com.google.inject.AbstractModule;
import play.libs.akka.AkkaGuiceSupport;
import actors.*;

import javax.inject.Inject;

public class MyModule extends AbstractModule implements AkkaGuiceSupport {
    @Inject
    MessageActor messageActor;
    @Override
    protected void configure() {
        bindActor(MessageActor.class, "message-actor");
        System.out.println("actor");
    }
}