package com.peaceful.demo.akka.setup;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.peaceful.demo.akka.actor.MonitoringScheduleActor;
import com.peaceful.demo.akka.actor.MonitoringScheduleActor2;

/**
 * Created by wangjun on 15/1/22.
 */
public class MonitoringScheduleDemo2 {

    public static ActorSystem system = ActorSystem.create("MySystem");

    public static void main(String[] args) {
        final ActorRef greeter = system.actorOf(Props.create(MonitoringScheduleActor2.class), "greeterActor");
        greeter.tell("game start", ActorRef.noSender());
    }
}
