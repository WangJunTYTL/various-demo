package com.peaceful.demo.akka.setup;

import akka.actor.*;
import akka.japi.Function;
import com.peaceful.common.util.Util;
import com.peaceful.demo.akka.actor.GreetActor5;
import com.peaceful.demo.akka.domain.Greeting;
import scala.concurrent.duration.Duration;



/**
 * <h3>Supervision</h3>
 * <p>
 * Actors form a tree with actors being parents to the actors they've created.
 * As a parent, the actor is responsible for handling its children’s failures (so-called supervision),
 * forming a chain of responsibility, all the way to the top. When an actor crashes,
 * its parent can either restart or stop it, or escalate the failure up the hierarchy of actors.
 * This enables a clean set of semantics for managing failures in a concurrent,
 * distributed system and allows for writing highly fault-tolerant systems that self-heal
 * </p>
 * Created by wangjun on 15/1/17.
 */
public class SupervisorDemo {
    public static void main(String[] args) throws InterruptedException {
        ActorSystem system = ActorSystem.create("MySystem");
        ActorRef greeter = system.actorOf(Props.create(Supervisor.class), "greeter");
        greeter.tell(new Greeting("wj"), ActorRef.noSender());
        greeter.tell(new Greeting("wj"), ActorRef.noSender());
        greeter.tell(new Greeting("wj"), ActorRef.noSender());
        for (; ; ) {
            greeter.tell(new Greeting("wj"), ActorRef.noSender());
            Thread.sleep(1000);
            Util.report("resume...");

        }
    }


}

/**
 * 不可以是public
 */
class Supervisor extends UntypedActor {

    private SupervisorStrategy strategy = new OneForOneStrategy(
            10, Duration.create("1 minute"), new Function<Throwable, SupervisorStrategy.Directive>() {
        @Override
        public SupervisorStrategy.Directive apply(Throwable t) {
            if (t instanceof ArithmeticException) return SupervisorStrategy.resume();
            else if (t instanceof NullPointerException) return SupervisorStrategy.restart();
            else if (t instanceof Exception) return SupervisorStrategy.stop();
            else return SupervisorStrategy.escalate();
        }
    });

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }

    ActorRef worker = getContext().actorOf(Props.create(GreetActor5.class));

    public void onReceive(Object message) throws Exception {
        if (message instanceof Greeting) worker.forward(message, getContext());
    }
}
