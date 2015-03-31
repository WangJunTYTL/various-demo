package com.peaceful.demo.akka.setup;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.peaceful.demo.akka.actor.Master;

/**
 * base actor test
 * <p/>
 * Created by wangjun on 15/1/17.
 */
public class RouterActorDemo {

    // ActorSystem is a heavy object: create only one per application
    final static ActorSystem system = ActorSystem.create("MySystem");

    public static void main(String[] args) {
        ActorRef master = system.actorOf(Props.create(Master.class), "master");
        master.tell("router test",master);
        master.tell("router test2",master);
        master.tell("router test3",master);
        master.tell("router test4",master);
        master.tell("router test5",master);

    }
}

