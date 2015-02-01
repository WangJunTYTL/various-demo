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
public class MonitoringScheduleActor extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    int x = 0;

    @Override
    public void onReceive(Object message) throws Exception {
        x = (Integer) message;
//        Util.report("current x value ->" + x);
        if (x == 11) { //当x = 11 监控到任务已完成，停止监控
            log.info("监控到任务已经执行完毕，监控actor不在调度");
        } else {
            log.info("当前任务完成进度--------" + (x * 10) + "%");
            final ActorRef sch = getContext().actorOf(Props.create(MonitoringScheduleActor.class));
            //监控每隔10s打开检查
            MonitoringScheduleDemo.system.scheduler().scheduleOnce(Duration.create(1000, TimeUnit.MILLISECONDS),
                    new Runnable() {
                        @Override
                        public void run() {
                            sch.tell((x + 1), ActorRef.noSender());
                        }
                    }, MonitoringScheduleDemo.system.dispatcher());
        }
    }
}
