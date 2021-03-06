package com.peaceful.demo.akka.setup;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.peaceful.demo.akka.actor.ChildActor;

/**
 * Created by wangjun on 15/1/22.
 */
public class ChildActorDemo {

    public static ActorSystem system = ActorSystem.create("MySystem");


    public static void main(String[] args) {

        final ActorRef greeter = system.actorOf(Props.create(ChildActor.class), "childActor");
        greeter.tell("1", ActorRef.noSender());
        greeter.tell(1, ActorRef.noSender());
    }
}
