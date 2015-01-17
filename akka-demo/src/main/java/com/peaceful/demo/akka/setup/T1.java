package com.peaceful.demo.akka.setup;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.peaceful.demo.akka.actor.GreetActor;
import com.peaceful.demo.akka.domain.Greeting;

/**
 * 最简单的测试
 *
 * Created by wangjun on 15/1/17.
 */
public class T1 {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("MySystem");
        ActorRef greeter = system.actorOf(Props.create(GreetActor.class), "greeter");
        greeter.tell(new Greeting("wj"), ActorRef.noSender());
    }
}

