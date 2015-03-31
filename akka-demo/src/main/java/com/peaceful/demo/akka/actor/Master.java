package com.peaceful.demo.akka.actor;

import akka.actor.*;
import akka.japi.Function;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;
import scala.concurrent.duration.Duration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjun on 15/3/9.
 */

public class Master extends UntypedActor {

    Router router;

    {
        List<Routee> routees = new ArrayList<Routee>();
        for (int i = 0; i < 5; i++) {
            ActorRef r = getContext().actorOf(Props.create(ChildOfGreetActor.class));
            getContext().watch(r);
            routees.add(new ActorRefRoutee(r));
        }
        router = new Router(new RoundRobinRoutingLogic(), routees);
    }

    public void onReceive(Object msg) {
        if (msg instanceof String) {
            router.route(msg, getSender());
        } else if (msg instanceof Terminated) {
            router = router.removeRoutee(((Terminated) msg).actor());
            ActorRef r = getContext().actorOf(Props.create(Worker.class));
            getContext().watch(r);
            router = router.addRoutee(new ActorRefRoutee(r));
        } else
            unhandled(msg);
    }

    // manage child strategy
    @Override
    public SupervisorStrategy supervisorStrategy() {
        SupervisorStrategy strategy = new OneForOneStrategy(
                10, Duration.create("1 minute"), new Function<Throwable, SupervisorStrategy.Directive>() {
            @Override
            public SupervisorStrategy.Directive apply(Throwable t) {
                if (t instanceof ArithmeticException) return SupervisorStrategy.resume();
                else if (t instanceof NullPointerException) return SupervisorStrategy.restart();
                else if (t instanceof Exception)
                    return SupervisorStrategy.stop(); // can’t receive message when actor stop,the message will dump to dead letters
                else return SupervisorStrategy.escalate();
            }
        });
        return strategy;
    }
}