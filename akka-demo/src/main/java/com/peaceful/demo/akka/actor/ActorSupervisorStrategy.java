package com.peaceful.demo.akka.actor;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Function;
import scala.concurrent.duration.Duration;

/**
 * @author <a href="mailto:wangjuntytl@163.com">WangJun</a>
 * @version 1.0 16/1/4
 */
public class ActorSupervisorStrategy extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    ActorRef child = getContext().actorOf(Props.create(ActorSupervisorStrategyChild.class), "actorSupervisorStrategyChild");
    ActorRef actorRef = getContext().watch(child);// 这个用于监控是否存活,还没太搞明白


    @Override
    public void preStart() throws Exception {
        log.info("{} preStart", getSelf().path().name());
        log.info("actorRef is {}", actorRef.path().name());
        super.preStart();
    }

    @Override
    public void onReceive(Object message) throws Exception {
        child.tell(message, getSelf());
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
