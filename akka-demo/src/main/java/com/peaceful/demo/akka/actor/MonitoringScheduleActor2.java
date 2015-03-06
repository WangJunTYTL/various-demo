package com.peaceful.demo.akka.actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.peaceful.demo.akka.setup.MonitoringScheduleDemo;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;


/**
 * 模拟监控任务是否完成，当完成时关闭监控
 * <p/>
 * Created by wangjun on 15/1/22.
 */
public class MonitoringScheduleActor2 extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    ActorRef child = getContext().actorOf(Props.create(ScheduleActor.class));

    @Override
    public void onReceive(Object message) throws Exception {
        child.tell("0", getSelf());
    }
}
