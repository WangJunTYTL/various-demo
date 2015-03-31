package com.peaceful.demo.akka.setup;

import akka.actor.*;
import akka.routing.RoundRobinPool;
import com.peaceful.demo.akka.actor.ChildActor;
import com.peaceful.demo.akka.actor.ChildOfGreetActor;
import com.peaceful.demo.akka.actor.Master;
import scala.concurrent.duration.Duration;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangjun on 15/3/14.
 */
public class RouterDemo2 {

    final static ActorSystem system = ActorSystem.create("MySystem");

    public static void main(String[] args) {

        ActorRef router = system.actorOf(Props.create(Master.class), "routerTest");
        router.tell("ok",router);
        router.tell("500",router);
        router.tell("ok",router);
        router.tell("302",router);
        router.tell("ok",router);
        router.tell("ok",router);
        router.tell("ok", router);
    }
}
