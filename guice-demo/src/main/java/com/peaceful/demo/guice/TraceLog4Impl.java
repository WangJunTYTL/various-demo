package com.peaceful.demo.guice;

import com.google.inject.Singleton;

/**
 * Created by wangjun on 16/2/19.
 */
@Singleton
public class TraceLog4Impl implements TraceLogI4 {

    @Override
    public void log(String msg) {
        System.out.println("charge trace3: " + msg);
    }
}
