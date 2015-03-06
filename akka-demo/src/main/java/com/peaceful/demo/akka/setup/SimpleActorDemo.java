package com.peaceful.demo.akka.setup;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.peaceful.demo.akka.actor.GreetActor;
import com.peaceful.demo.akka.domain.Greeting;

/**
 * 最简单的测试
 * <p/>
 * Created by wangjun on 15/1/17.
 */
public class SimpleActorDemo {

    // ActorSystem is a heavy object: create only one per application
    final static ActorSystem system = ActorSystem.create("MySystem");

    public static void main(String[] args) {
        //Using the ActorSystem will create top-level actors,
        // supervised by the actor system’s provided guardian actor,
        // while using an actor’s context will create a child actor.
        // 创建一个actor的实例
        //The name parameter is optional, but you should preferably name your actors,
        // since that is used in log messages and for identifying actors.
        // The name must not be empty or start with $, but it may contain URL encoded characters (eg. %20 for a blank space).
        // If the given name is already in use by another child to the same parent an InvalidActorNameException is thrown.
        // actorref 代表一个actor实例
        ActorRef greeter = system.actorOf(Props.create(GreetActor.class), "$greet");
        greeter.tell(new Greeting("$wj"), ActorRef.noSender());
    }
}

