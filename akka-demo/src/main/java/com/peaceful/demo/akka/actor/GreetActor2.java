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
public class GreetActor2 extends UntypedActor {

    public Date date;

    public GreetActor2(Date date) {
        this.date = date;
    }

    @Override
    public void onReceive(Object message) throws Exception {
        LoggingAdapter log = Logging.getLogger(getContext().system(), this);
        log.info(message.toString());
        if (message instanceof Greeting)
            log.info("Hello " + ((Greeting) message).who + " now time is " + date.toLocaleString());
    }
}
