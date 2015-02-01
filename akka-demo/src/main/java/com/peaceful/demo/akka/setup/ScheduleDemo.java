package com.peaceful.demo.akka.setup;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Cancellable;
import akka.actor.Props;
import com.peaceful.demo.akka.actor.GreetActor;
import com.peaceful.demo.akka.domain.Greeting;
import com.peaceful.demo.akka.domain.Sms;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangjun on 15/1/22.
 */
public class ScheduleDemo {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("MySystem");
        final ActorRef greeter = system.actorOf(Props.create(GreetActor.class), "greeterActor");
        Cancellable cancellable = null;
        cancellable = system.scheduler().schedule(Duration.Zero(),
                Duration.create(50, TimeUnit.MILLISECONDS), greeter, "wj",
                system.dispatcher(), null);
//This cancels further Ticks to be sent
        cancellable.cancel();
    }
}
