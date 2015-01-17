package com.peaceful.demo.akka.actor;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.peaceful.demo.akka.domain.Greeting;
import com.peaceful.demo.akka.domain.Sms;

import java.util.Date;

/**
 * 发短信是一个耗时的过程，模拟发送短信过程
 * <p/>
 * Created by wangjun on 15/1/17.
 */
public class SmsActor extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        LoggingAdapter log = Logging.getLogger(getContext().system(), this);
        if (message instanceof Sms)
            log.info("sms start send... ");
        log.info("sms start send content: " + ((Sms) message).content);
        log.info("sms start send receiver: " + ((Sms) message).receiver);
        log.info("sms start send sender: " + ((Sms) message).sender);
        Thread.sleep(2000);
        log.info("sms start send suc ...  ");
    }
}




