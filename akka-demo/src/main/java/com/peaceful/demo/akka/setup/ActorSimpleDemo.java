package com.peaceful.demo.akka.setup;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.peaceful.demo.akka.actor.ActorSimple;

/**
 * @author <a href="mailto:wangjuntytl@163.com">WangJun</a>
 * @version 1.0 16/1/6
 */
public class ActorSimpleDemo {

    // 启动Actor system, only once
    final static ActorSystem system = ActorSystem.create("MySystem");
    static LoggingAdapter log = Logging.getLogger(system,ActorSimpleDemo.class);


    public static void main(String[] args) {

        ActorRef simple01 = system.actorOf(Props.create(ActorSimple.class));
        ActorRef simple02 = system.actorOf(Props.create(ActorSimple.class));
        simple01.tell("hello world",ActorRef.noSender());
        simple02.tell("error",ActorRef.noSender());
        log.info("good!");



    }
}
