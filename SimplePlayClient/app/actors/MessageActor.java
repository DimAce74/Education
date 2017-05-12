package actors;

import akka.actor.AbstractActor;

/**
 * Created by db2admin on 11.05.2017.
 */
public class MessageActor extends AbstractActor {


    @Override
    public Receive createReceive() {
        return receiveBuilder().match(String.class, System.out::println).build();
    }
}
