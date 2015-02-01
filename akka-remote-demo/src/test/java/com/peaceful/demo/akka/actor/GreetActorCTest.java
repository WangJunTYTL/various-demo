package com.peaceful.demo.akka.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.peaceful.demo.akka.domain.Greeting;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class GreetActorCTest {

    @Test
    public void testCreate() throws Exception {
        ActorSystem system = ActorSystem.create("MySystem");
        ActorRef greeter = system.actorOf(Props.create(GreetActor.class), "greeter");
        greeter.tell(new Greeting("wj"), ActorRef.noSender());
    }

    @Test
    public void testCreate2() throws Exception {
        ActorSystem system = ActorSystem.create("MySystem");
        ActorRef greeter = system.actorOf(Props.create(GreetActor2.class,new Date()), "greeter");
        greeter.tell(new Greeting("wj"), ActorRef.noSender());
    }
}