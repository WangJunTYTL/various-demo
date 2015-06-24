package com.peaceful.demo.akka.setup;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.peaceful.demo.akka.actor.PersistentActor;
import com.peaceful.demo.akka.actor.PersistentViewActor;

/**
 * base actor test
 * <p/>
 * Created by wangjun on 15/1/17.
 */
public class PersistentViewActorDemo {

    // ActorSystem is a heavy object: create only one per application
    final static ActorSystem system = ActorSystem.create("MySystem");



    public static void main(String[] args) {
        // actorref 代表一个actor实例
        ActorRef greeter = system.actorOf(Props.create(PersistentViewActor.class), "test-persistent-view"); // 同一个parent actor 下不可以有同名的child
//        greeter.tell("print", ActorRef.noSender());

    }
}

