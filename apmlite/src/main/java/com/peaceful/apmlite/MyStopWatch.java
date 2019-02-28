package com.peaceful.apmlite;

import org.perf4j.LoggingStopWatch;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by wangjun on 2018-12-29.
 */
public class MyStopWatch extends LoggingStopWatch {

    private static UseNopLog myAppender;

    static {
        try {
            myAppender = new UseNopLog(new MyRDB("mysql"),60000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } myAppender.start();
    }

    @Override
    protected void log(String stopWatchAsString, Throwable exception) {
        myAppender.append(stopWatchAsString);
    }
}
