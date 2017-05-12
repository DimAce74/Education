package actors;

import akka.actor.*;
import scala.concurrent.duration.Duration;

import static java.util.concurrent.TimeUnit.SECONDS;

public class LookupActor extends AbstractActor {

  private final String path;
  private ActorRef messageActor = null;

  public LookupActor(String path) {
    this.path = path;
    sendIdentifyRequest();
  }

  private void sendIdentifyRequest() {
    getContext().actorSelection(path).tell(new Identify(path), self());
      System.out.println("sending Identify");
    getContext()
        .system()
        .scheduler()
        .scheduleOnce(Duration.create(3, SECONDS), self(),
            ReceiveTimeout.getInstance(), getContext().dispatcher(), self());
  }

  @Override
  public Receive createReceive() {
    return receiveBuilder()
      .match(ActorIdentity.class, identity -> {
          System.out.println(identity);
        messageActor = identity.getRef();
        if (messageActor == null) {
          System.out.println("Remote actor not available: " + path);
        } else {
          getContext().watch(messageActor);
          getContext().become(active, true);
        }
      })
      .match(ReceiveTimeout.class, x -> {
        sendIdentifyRequest();
      })
      .build();
  }

  Receive active = receiveBuilder()
    .match(String.class, message -> {
      // send message to server actor
      messageActor.tell(message, self());
    })
    .match(Terminated.class, terminated -> {
      System.out.println("Calculator terminated");
      sendIdentifyRequest();
      getContext().unbecome();
    })
    .match(ReceiveTimeout.class, message -> {
      // ignore
    })
    .build();

}
