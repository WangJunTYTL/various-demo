package com.peaceful.demo.akka.actor;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * @author <a href="mailto:wangjuntytl@163.com">WangJun</a>
 * @version 1.0 16/1/4
 */
public class ActorState extends UntypedActor {

    public int count = 0;

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof String){
            if (message.equals("++")){
                count++;
                Thread.sleep(5000);
                log.info("current count value is {}",count);
            }else if (message.equals("--")){
                count--;
                Thread.sleep(2000);
                log.info("current count value is {}",count);
            }else {
                unhandled(message);
            }
        }else {
            unhandled(message);
        }
    }
}
