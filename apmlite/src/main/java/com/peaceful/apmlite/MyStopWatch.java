package com.peaceful.apmlite;

import org.perf4j.LoggingStopWatch;

/**
 * Created by wangjun on 2018-12-29.
 */
public class MyStopWatch extends LoggingStopWatch {

    private static UseNopLog myAppender = new UseNopLog();

    static {
        myAppender.start();
    }

    @Override
    protected void log(String stopWatchAsString, Throwable exception) {
        myAppender.append(stopWatchAsString);
    }
}
