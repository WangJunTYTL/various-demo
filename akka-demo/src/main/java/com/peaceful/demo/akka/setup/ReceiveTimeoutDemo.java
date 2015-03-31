package com.peaceful.demo.akka.setup;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;
import com.peaceful.demo.akka.actor.GreetActor;
import com.peaceful.demo.akka.actor.GreetActor6;
import com.peaceful.demo.akka.domain.Greeting;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangjun on 15/1/17.
 */
public class ReceiveTimeoutDemo {
    public static void main(String[] args) throws InterruptedException {
        ActorSystem system = ActorSystem.create("MySystem");
        ActorRef actorRef = system.actorOf(Props.create(GreetActor6.class));
        actorRef.tell(new Greeting("hello"),actorRef);
        Thread.sleep(5000);
        actorRef.tell(new Greeting("hello"), actorRef);

        //You can also send an actor the akka.actor.PoisonPill message, which will stop the actor when the message is processed. PoisonPill is enqueued as ordinary messages and will be handled after messages that were already queued in the mailbox.

          //      Use it like this:
        //发送PoisonPill 消息 ，stop actor
        actorRef.tell(akka.actor.PoisonPill.getInstance(), actorRef);
        // 消息发不出去，进入deadletter
        actorRef.tell(new Greeting("hello"), actorRef);


    }
}


