package com.peaceful.demo.perf4j;

import org.perf4j.StopWatch;
import org.perf4j.log4j.Log4JStopWatch;
import org.perf4j.slf4j.Slf4JStopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wangjun
 * @version 1.0
 * @since 15/4/24.
 */

public class T2 {
    public static void main (String[] args) throws Exception {
        // note that the log4j framework will automatically load the log4j.xml
        // file if it is in a root directory on the classpath

        Logger rootLogger = LoggerFactory.getLogger("test");

        for (int i = 0; i < 50; i++) {
            // By default the Log4JStopWatch uses the Logger named org.perf4j.TimingLogger
//            StopWatch stopWatch = new Log4JStopWatch();
            StopWatch stopWatch = new Slf4JStopWatch();

            // for demo purposes just sleep
            Thread.sleep((long) (1000));

            rootLogger.info("Normal logging messages only go to the console");

            // Calling lap() stops timing for the previous block, sends the
            // message to the log4j Logger, and starts timing the next block.
            stopWatch.lap("firstBlock");
//            stopWatch.lap("firstBlock","hello world");

            Thread.sleep((long) (2000));

            stopWatch.stop("secondBlock");
        }

    }
}
