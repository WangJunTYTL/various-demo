package com.peaceful.demo.akka.setup;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.peaceful.demo.akka.actor.ActorSupervisorStrategy;

/**
 *
 *
 * @author <a href="mailto:wangjuntytl@163.com">WangJun</a>
 * @version 1.0 16/1/4
 */
public class ActorWatchDemo {

    final static ActorSystem system = ActorSystem.create("mySystem");

    // 日志组件本身也是利用Actor实现
    static LoggingAdapter log = Logging.getLogger(system,ActorWatchDemo.class);

    public static void main(String[] args) {

        ActorRef actorRef = system.actorOf(Props.create(ActorSupervisorStrategy.class),"actorSupervisorStrategy");
        actorRef.tell("error",ActorRef.noSender());
        log.info("----end------------------------------");
    }
}
