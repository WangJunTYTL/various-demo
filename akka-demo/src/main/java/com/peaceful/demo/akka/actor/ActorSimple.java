package com.peaceful.demo.akka.actor;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Function;
import scala.concurrent.duration.Duration;

/**
 * @author <a href="mailto:wangjuntytl@163.com">WangJun</a>
 * @version 1.0 16/1/6
 */
public class ActorSimple extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    ActorRef errorChild = getContext().actorOf(Props.create(ActorError.class), "error");


    @Override
    public void onReceive(Object message) throws Exception {
        // 先简单的输出一句话
        Thread.sleep(5000);
        log.info("event is {}", message);
        sender().tell("HI!", self());
        errorChild.tell(message, self());
    }

    @Override
    public SupervisorStrategy supervisorStrategy() {
        final SupervisorStrategy strategy = new OneForOneStrategy(
                10, Duration.create("1 minute"), new Function<Throwable, SupervisorStrategy.Directive>() {
            @Override
            public SupervisorStrategy.Directive apply(Throwable t) {
                if (t instanceof ArithmeticException) {
                    log.warning("ArithmeticException resume");
                    return SupervisorStrategy.resume();
                } else if (t instanceof NullPointerException) {
                    // 重启后可以继续处理它的mailbox
                    log.warning("NullPointerException start");
                    return SupervisorStrategy.restart();
                } else if (t.getMessage() != null && t.getMessage().equals("escalate")) {
                    // 向上级报告错误
                    log.warning("escalate");
                    return SupervisorStrategy.escalate();
                } else if (t instanceof Exception) {
                    // can’t receive message when actor stop,the message will dump to dead letters
                    log.warning("Exception stop");
                    return SupervisorStrategy.stop();
                } else {
                    // 向上级报告错误
                    return SupervisorStrategy.escalate();
                }
            }
        });
        return strategy;
    }
}
