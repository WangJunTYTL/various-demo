package com.peaceful.demo.akka.actor;

import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * @author <a href="mailto:wangjuntytl@163.com">WangJun</a>
 * @version 1.0 16/1/4
 */
public class ActorSupervisorStrategyChild extends UntypedActor {

    LoggingAdapter loggingAdapter = Logging.getLogger(getContext().system(), this);

    @Override
    public void onReceive(Object message) throws Exception {
        loggingAdapter.info("{} message is {}", getSelf().path().name(), message);
        if (message instanceof String) {
            if (message.equals("ArithmeticException")) {
                int x = 1 / 0;
            } else if (message.equals("null")) {
                String c = "aa".concat(null);
                Thread.sleep(2000);
            } else if (message.equals("error")) {
                throw new Exception("error");
            } else if (message.equals("escalate")) {
                throw new Exception("escalate");
            } else {
                loggingAdapter.info("good !");
            }
        }else if (message instanceof Terminated){
            loggingAdapter.info("message is terminated");
        }
        else {
            unhandled(message);
        }
    }
}
