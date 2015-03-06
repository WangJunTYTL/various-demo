package com.peaceful.demo.akka.actor;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangjun on 15/3/6.
 */
public class ScheduleActor extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    final String flag = "game over";

    @Override
    public void onReceive(final Object o) throws Exception {
        log.info("last process is " + o);
        if (o.equals(flag)) {
            log.info("game over");
        } else if (o.equals("100")) {
            getSender().tell("game over", getSender());
        } else {
            getContext().system().scheduler().scheduleOnce(Duration.create(1000, TimeUnit.MILLISECONDS),
                    new Runnable() {
                        @Override
                        public void run() {
                            getSelf().tell(String.valueOf(Integer.valueOf((String) o) + 10), getSelf());

                        }
                    }, getContext().system().dispatcher());
        }
    }

}
