package com.peaceful.demo.akka.setup;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.peaceful.demo.akka.actor.ActorSupervisorStrategy;

/**
 *
 * actor 监管策略测试
 *
 * actor重启后,仍然可以消费它的mailbox,但Stop后,会把xmailbox的剩余消息发送到死亡邮箱
 *
 * @author <a href="mailto:wangjuntytl@163.com">WangJun</a>
 * @version 1.0 16/1/4
 */
public class ActorSupervisorStrategyDemo {

    final static ActorSystem system = ActorSystem.create("mySystem");

    // 日志组件本身也是利用Actor实现
    static LoggingAdapter log = Logging.getLogger(system,ActorSupervisorStrategyDemo.class);

    public static void main(String[] args) {
        ActorRef actorRef = system.actorOf(Props.create(ActorSupervisorStrategy.class),"actorSupervisorStrategy");
        actorRef.tell("good",ActorRef.noSender());
        actorRef.tell("ArithmeticException",ActorRef.noSender());
        actorRef.tell("good",ActorRef.noSender());
        actorRef.tell("null",ActorRef.noSender());
        actorRef.tell("good",ActorRef.noSender());
        actorRef.tell("escalate",ActorRef.noSender());
        actorRef.tell("good",ActorRef.noSender());
        actorRef.tell("error",ActorRef.noSender());
        actorRef.tell("good",ActorRef.noSender());
        log.info("----end------------------------------");
    }
}
