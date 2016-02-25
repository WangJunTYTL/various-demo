package com.peaceful.demo.akka.setup;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.peaceful.common.util.Util;
import com.peaceful.demo.akka.actor.GreetActor;
import com.peaceful.demo.akka.domain.Greeting;

/**
 * base actor test
 * <p/>
 * Created by wangjun on 15/1/17.
 */
public class SimpleActorDemo {

    // ActorSystem is a heavy object: create only one per application
    final static ActorSystem system = ActorSystem.create("MySystem");

    public static void main(String[] args) throws InterruptedException {
        //Using the ActorSystem will create top-level actors,supervised by the actor system’s provided guardian actor,
        // while using an actor’s context will create a child actor.

        //The name parameter is optional, but you should preferably name your actors,
        // since that is used in log messages and for identifying actors.
        // The name must not be empty or start with $, but it may contain URL encoded characters (eg. %20 for a blank space).
        // If the given name is already in use by another child to the same parent an InvalidActorNameException is thrown.
        // actorref 代表一个actor实例
        ActorRef greeter = system.actorOf(Props.create(GreetActor.class), "greet"); // 同一个parent actor 下不可以有同名的child
        greeter.tell(new Greeting("wj"), ActorRef.noSender()); // 发送消息方式： greeter.tell  is message address ，params  is message content and sender
        greeter.tell("wj", greeter);  // message forward
//        greeter.tell("500", greeter);  // test SupervisorStrategy
        greeter.tell("302", greeter);  // test SupervisorStrategy
        greeter.tell("child Whether can continue to run ...", greeter);  // test SupervisorStrategy
        //actor path
        system.actorSelection("/user/greet").tell(new Greeting("give me a word-01"),ActorRef.noSender());
        // 找不到actor，消息将会被发送到deadLetter
        system.actorSelection("/user/greet2").tell(new Greeting("give me a word-02"),ActorRef.noSender());
        system.actorSelection("/user/greet2").tell(new Greeting("give me a word-03"),ActorRef.noSender());
        system.actorSelection("/user/greet2").tell(new Greeting("give me a word-04"),ActorRef.noSender());
        system.actorSelection(greeter.path()).tell(new Greeting("give me a word-05"),ActorRef.noSender());
    }
}

