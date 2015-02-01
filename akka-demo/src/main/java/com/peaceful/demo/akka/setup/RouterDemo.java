package com.peaceful.demo.akka.setup;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.FromConfig;
import akka.routing.RoundRobinRouter;
import akka.routing.SmallestMailboxRouter;
import com.peaceful.demo.akka.actor.GreetActor;
import com.peaceful.demo.akka.actor.GreetActor3;
import com.peaceful.demo.akka.domain.Greeting;

/**
 * actor router
 * <p/>
 * Created by wangjun on 15/1/17.
 */
public class RouterDemo {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("MySystem");
        ActorRef greeter = system.actorOf(Props.create(GreetActor3.class).withRouter(new FromConfig()), "myrouter1");
        for (; ; ) {
            greeter.tell(new Greeting("wj"), ActorRef.noSender());
        }
    }
}

