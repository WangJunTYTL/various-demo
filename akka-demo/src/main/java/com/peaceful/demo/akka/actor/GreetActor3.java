package com.peaceful.demo.akka.actor;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.peaceful.demo.akka.domain.Greeting;

import java.util.Date;

/**
 * 需要有构造参数的actor
 * Created by wangjun on 15/1/17.
 */
public class GreetActor3 extends UntypedActor {


    @Override
    public void onReceive(Object message) throws Exception {
        LoggingAdapter log = Logging.getLogger(getContext().system(), this);
        log.info(message.toString());
        if (message instanceof Greeting)
            log.info("Hello " + ((Greeting) message).who );
        Class.forName("com.wj.T1");
    }
}
