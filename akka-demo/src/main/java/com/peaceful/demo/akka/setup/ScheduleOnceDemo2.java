package com.peaceful.demo.akka.setup;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.peaceful.demo.akka.actor.GreetActor;
import com.peaceful.demo.akka.domain.Greeting;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangjun on 15/1/22.
 */
public class ScheduleOnceDemo2 {

    final static ActorSystem system = ActorSystem.create("MySystem");
    final static LoggingAdapter log = Logging.getLogger(system, ScheduleOnceDemo2.class);

    public static void main(String[] args) {
        system.scheduler().maxFrequency();
        for (int i = 0; i < 1000; i++) {
            system.scheduler().scheduleOnce(Duration.create(1000, TimeUnit.MILLISECONDS), new Task(1000), system.dispatcher());
        }
        log.info("{}", system.scheduler().maxFrequency());
        log.info("--------------");
    }


    private static class Task implements Runnable {

        public long startTime = System.currentTimeMillis();

        public Task(long delay) {
            startTime += delay;
        }

        @Override
        public void run() {
            log.info("启动时间延迟:{} ms",System.currentTimeMillis() - startTime);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("hello world");
        }
    }
}
