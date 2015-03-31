package com.peaceful.demo.akka.setup;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;
import com.peaceful.demo.akka.actor.GreetActor;
import com.peaceful.demo.akka.actor.GreetActor7;
import com.peaceful.demo.akka.actor.GreetActor8;
import com.peaceful.demo.akka.domain.Greeting;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangjun on 15/1/17.
 */
public class T4 {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("MySystem");
        ActorRef actorRef = system.actorOf(Props.create(GreetActor7.class));
        ActorRef actorRef2 = system.actorOf(Props.create(GreetActor8.class));
        String msg = "测试actor个数与消息处理的速度";
        String msg2 = "msg 2";
        actorRef.tell(new Greeting(msg), actorRef);
        actorRef.tell(new Greeting(msg), actorRef);
        actorRef.tell(new Greeting(msg), actorRef);
        actorRef.tell(new Greeting(msg), actorRef);
        actorRef.tell(new Greeting(msg), actorRef);
        // --------------------------
        actorRef2.tell(new Greeting(msg2), actorRef2);
        actorRef2.tell(new Greeting(msg2), actorRef2);
        actorRef2.tell(new Greeting(msg2), actorRef2);
        actorRef2.tell(new Greeting(msg2), actorRef2);
        actorRef2.tell(new Greeting(msg2), actorRef2);
    }
}


