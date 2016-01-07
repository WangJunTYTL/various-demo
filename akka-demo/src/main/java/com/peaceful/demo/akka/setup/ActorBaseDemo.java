package com.peaceful.demo.akka.setup;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.peaceful.demo.akka.actor.ActorBase;

/**
 * @author <a href="mailto:wangjuntytl@163.com">WangJun</a>
 * @version 1.0 16/1/5
 */
public class ActorBaseDemo {

    // 一个app最好只有一个实例
    final static ActorSystem system = ActorSystem.create("MySystem");
    final static LoggingAdapter log = Logging.getLogger(system,ActorBase.class);


    public static void main(String[] args) {
        // 启动一个Actor,ActorRef 相当于是对Actor的一个包装
        ActorRef actor = system.actorOf(Props.create(ActorBase.class),"base");
        // 向actor发送一条消息
        actor.tell("Hello world",ActorRef.noSender());
        log.info("----------");
    }

}
