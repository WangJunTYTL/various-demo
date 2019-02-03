package com.peaceful.apmlite;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.perf4j.StopWatch;
import org.perf4j.log4j.AsyncCoalescingStatisticsAppender;
import org.perf4j.log4j.Log4JStopWatch;

import java.util.Enumeration;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangjun on 2018-12-18.
 */
public class UseLog4j {

    public static void main(String[] args) throws InterruptedException {
        StopWatch watch = new Log4JStopWatch();
        Logger logger = Logger.getLogger(Log4JStopWatch.DEFAULT_LOGGER_NAME);
        Enumeration<Appender> appenders = logger.getAllAppenders();
        if (appenders == null || !appenders.hasMoreElements()) {
            System.out.println("apm monitor start,use log4j ......");
            // 用于统计
            AsyncCoalescingStatisticsAppender asyncCoalescingStatisticsAppender = new AsyncCoalescingStatisticsAppender();
            asyncCoalescingStatisticsAppender.setName("AsyncCoalescingStatisticsAppender");
            asyncCoalescingStatisticsAppender.setTimeSlice(10000L);

            // 结果输出
            ConsoleAppender consoleAppender = new ConsoleAppender();
            consoleAppender.setName("console");
            consoleAppender.setLayout(new SimpleLayout());
            consoleAppender.activateOptions();

            asyncCoalescingStatisticsAppender.addAppender(consoleAppender);
            asyncCoalescingStatisticsAppender.activateOptions();

            logger.addAppender(asyncCoalescingStatisticsAppender);
        }

        for (; ; ) {
            watch.start();
            TimeUnit.SECONDS.sleep(1);
            watch.lap("Test");
        }
    }
}
