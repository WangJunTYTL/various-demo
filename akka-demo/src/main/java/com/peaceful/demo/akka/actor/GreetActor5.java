package com.peaceful.demo.akka.actor;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.peaceful.common.util.Util;
import com.peaceful.demo.akka.domain.Greeting;

/**
 * Since Akka enforces parental supervision every actor is supervised and (potentially)
 * the supervisor of its children, it is advisable that you familiarize yourself
 * with Actor Systems and Supervision and Monitoring and it may also help to read Actor References, Paths and Addresses.
 *
 * Created by wangjun on 15/1/17.
 */
public class GreetActor5 extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        LoggingAdapter log = Logging.getLogger(getContext().system(), this);
        if (message instanceof Greeting)
            log.info("Hello " + ((Greeting) message).who);
        else{
            Util.report("exe ..."+message);
        }
        Class.forName("com.wj.unknow");
//        unhandled(message);
        int i = 1/0;
    }
}
