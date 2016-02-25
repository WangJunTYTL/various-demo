package com.peaceful.demo.guice;

/**
 * Created by wangjun on 16/2/19.
 */
public class TraceLog2Impl implements TraceLogI2 {

    @Override
    public void log(String msg) {
        System.out.println("charge trace2: " + msg);
    }
}
