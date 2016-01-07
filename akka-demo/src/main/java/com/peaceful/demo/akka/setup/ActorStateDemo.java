package com.peaceful.demo.akka.setup;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.peaceful.demo.akka.actor.ActorState;

/**
 * Actor的State安全测试
 *
 * @author <a href="mailto:wangjuntytl@163.com">WangJun</a>
 * @version 1.0 16/1/4
 */
public class ActorStateDemo {

    final static ActorSystem system = ActorSystem.create("MySystem");
    static LoggingAdapter log = Logging.getLogger(system,ActorStateDemo.class);

    public static void main(String[] args) {
        ActorRef actorRef01 = system.actorOf(Props.create(ActorState.class));
        ActorRef actorRef02 = system.actorOf(Props.create(ActorState.class));
        actorRef01.tell("++",ActorRef.noSender());
        actorRef01.tell("--",ActorRef.noSender());
        actorRef02.tell("--",ActorRef.noSender());
        log.info("-------------------------------------");
    }
}
