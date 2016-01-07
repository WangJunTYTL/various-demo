package com.peaceful.demo.akka.actor;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * @author <a href="mailto:wangjuntytl@163.com">WangJun</a>
 * @version 1.0 16/1/6
 */
public class ActorError extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(),this);

    @Override
    public void onReceive(Object message) throws Exception {
        log.info("{} actor receive message is {}",self().path().name(),message);
        if (message instanceof String){
            if (message.equals("error")) {
                int x = 1 / 0;
            }else {
                unhandled(message);
            }
        }else {
            unhandled(message);
        }
    }
}
