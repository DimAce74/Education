package controllers;

import actors.MessageActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

/**
 * Created by db2admin on 12.05.2017.
 */
public class Application {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("message", ConfigFactory.load("message"));
        System.out.println(system);

        ActorRef actor = system.actorOf(Props.create(MessageActor.class), "message-actor");
        System.out.println(actor);

    }
}

