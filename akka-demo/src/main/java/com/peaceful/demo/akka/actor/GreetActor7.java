package com.peaceful.demo.akka.actor;

import akka.actor.ReceiveTimeout;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.peaceful.demo.akka.domain.Greeting;
import scala.concurrent.duration.Duration;

/**
 * setReceiveTimeout
 *
 * Created by wangjun on 15/1/17.
 */
public class GreetActor7 extends UntypedActor { // 创建一个actor 要实现 UntypedActor

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);


    public GreetActor7() {
        //the UntypedActorContext setReceiveTimeout defines the inactivity timeout after which the sending of a ReceiveTimeout message is triggered.
        // When specified, the receive function should be able to handle an akka.actor.ReceiveTimeout message.
        // 1 millisecond is the minimum supported timeout.

        // To set an initial delay
        getContext().setReceiveTimeout(Duration.create("3 seconds"));
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Greeting){
            log.info("Hello " + ((Greeting) message).who);
            Thread.sleep(5000);
            getContext().setReceiveTimeout(Duration.create("3 seconds")); // 下次多少时间后没有接到消息，发送  ReceiveTimeout message
        }
        else if (message instanceof ReceiveTimeout) {
            // To turn it off
            log.info("msg timeout");
            getContext().setReceiveTimeout(Duration.Undefined());
        } else {
            unhandled(message);
        }
    }

    @Override
    public void postStop() throws Exception {
        log.info(getSelf().path().name()+" stop !!!");
        super.postStop();
    }
}
