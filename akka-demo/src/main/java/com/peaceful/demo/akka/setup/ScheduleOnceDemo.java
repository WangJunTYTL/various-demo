package com.peaceful.demo.akka.setup;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.peaceful.demo.akka.actor.GreetActor;
import com.peaceful.demo.akka.domain.Greeting;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangjun on 15/1/22.
 */
public class ScheduleOnceDemo {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("MySystem");
        final ActorRef greeter = system.actorOf(Props.create(GreetActor.class), "wj");
//        system.scheduleOnce(50 milliseconds, testActor, "foo")
        system.scheduler().scheduleOnce(Duration.create(50, TimeUnit.MILLISECONDS),
                new Runnable() {
                    @Override
                    public void run() {
                        greeter.tell(new Greeting("wj"), ActorRef.noSender());
                    }
                }, system.dispatcher());

    }


    public void test() {
        ActorSystem system = ActorSystem.create("MySystem");
        final ActorRef greeter = system.actorOf(Props.create(GreetActor.class), "wj");
        system.scheduler().scheduleOnce(Duration.create(50, TimeUnit.MILLISECONDS),
                new Runnable() {
                    @Override
                    public void run() {
                        greeter.tell(new Greeting("wj"), ActorRef.noSender());
                    }
                }, system.dispatcher());

    }
}
