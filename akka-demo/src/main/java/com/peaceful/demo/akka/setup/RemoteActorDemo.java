package com.peaceful.demo.akka.setup;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.japi.Creator;
import com.peaceful.demo.akka.actor.GreetActor;
import com.peaceful.demo.akka.actor.GreetActor2;
import com.peaceful.demo.akka.domain.Greeting;

import java.util.Date;

/**
 * 获取远程actor
 * <p/>
 * Created by wangjun on 15/1/17.
 */
public class RemoteActorDemo {

    public static void main(String[] args) {

        // on machine 3: Remote Lookup (logical home of “greeter” is machine2, remote deployment is transparent)
        ActorSystem system = ActorSystem.create("MySystem");
        ActorSelection greeter = system.actorSelection("akka.tcp://MySystem@127.0.0.1:2553/user/greeter");
        for (int i = 0; i < 1000; i++) {
            greeter.tell(new Greeting("Sonny Rollins"), ActorRef.noSender());
        }
    }
}


