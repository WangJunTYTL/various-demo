package com.peaceful.demo.perf4j;

import com.peaceful.common.util.Util;
import org.perf4j.LoggingStopWatch;
import org.perf4j.StopWatch;

/**
 * @author wangjun
 * @version 1.0
 * @since 15/4/24.
 */

public class T1 {

    public static void main(String[] args) throws InterruptedException {
        t1();
        t2();
    }

    public static void t1() throws InterruptedException {
        Util.report("t1");
        Util.dashed();
        // Create the LoggingStopWatch to start timing. This constructor takes the tag
// name, which identifies the code block being timed. Note that usually you
// will want to instantiate one of the subclasses of LoggingStopWatch, such as
// a Log4JStopWatch or CommonsLogStopWatch depending on your preferred
// logging framework.
        StopWatch stopWatch = new LoggingStopWatch("codeBlock1");

// Execute the code block - this is just a dummy call to pause execution for
// 0-1 second.
        Thread.sleep((long) (Math.random() * 1000L));

// Stop the StopWatch and log it. LoggingStopWatches automatically log their
// timing statements when one of the stop() or lap() methods are called.
        stopWatch.stop();
        Util.enter();
    }

    public static void t2() {
        Util.report("t2");
        Util.dashed();
        StopWatch stopWatch = new LoggingStopWatch();
        try {
            // the code block being timed - this is just a dummy example
            long sleepTime = (long) (Math.random() * 1000L);
            Thread.sleep(sleepTime);
            if (sleepTime > 500L) {
                throw new Exception("Throwing exception");
            }
            stopWatch.stop("codeBlock2.success", "Sleep time was < 500 ms");
        } catch (Exception e) {
            stopWatch.stop("codeBlock2.failure", "Exception was: " + e);
        }
    }
}
