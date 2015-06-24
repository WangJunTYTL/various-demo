package com.peaceful.demo.akka.setup;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.peaceful.demo.akka.actor.Cmd;
import com.peaceful.demo.akka.actor.GreetActor;
import com.peaceful.demo.akka.actor.PersistentActor;
import com.peaceful.demo.akka.domain.Greeting;

/**
 * base actor test
 * <p/>
 * Created by wangjun on 15/1/17.
 */
public class PersistentActorDemo {

    // ActorSystem is a heavy object: create only one per application
    final static ActorSystem system = ActorSystem.create("MySystem");



    public static void main(String[] args) {
        // actorref 代表一个actor实例
        ActorRef greeter = system.actorOf(Props.create(PersistentActor.class), "test-persistent"); // 同一个parent actor 下不可以有同名的child
//        Cmd cmd = new Cmd("cmd-data");
//        greeter.tell(cmd, ActorRef.noSender());
        greeter.tell("print", ActorRef.noSender());
        greeter.tell("snap", ActorRef.noSender());

    }
}

