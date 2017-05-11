package actors;

import akka.actor.AbstractActor;
import play.Configuration;

import javax.inject.Inject;

/**
 * Created by db2admin on 11.05.2017.
 */
public class MessageActor extends AbstractActor {

    Configuration configuration;

    @Inject
    public MessageActor(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Receive createReceive() {

        return sender().tell();
    }
}
