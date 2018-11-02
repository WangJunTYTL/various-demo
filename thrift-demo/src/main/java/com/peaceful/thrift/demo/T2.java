package com.peaceful.thrift.demo;

import org.perf4j.StopWatch;
import org.perf4j.log4j.Log4JStopWatch;

/**
 * Created by Jun on 2018/9/8.
 */
public class T2 {

    public static void main(String[] args) {
        for (;;) {
            StopWatch stopWatch = new Log4JStopWatch("Test");
            stopWatch.stop();
        }
    }
}
