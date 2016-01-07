package com.peaceful.demo.akka.actor;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * 怎样创建一个Actor
 *
 * @author <a href="mailto:wangjuntytl@163.com">WangJun</a>
 * @version 1.0 16/1/5
 */
public class ActorBase extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    @Override
    public void onReceive(Object message) throws Exception {
        // 收到消息
        log.info("sender is {},message is {}", sender().path(), message);
        // 回复发送者
        sender().tell("Hi!", self());
    }
}
