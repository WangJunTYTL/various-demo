package com.peaceful.demo.guice;

/**
 * Created by wangjun on 16/2/19.
 */
//@Singleton // 表明是单例，默认是每次都会实例化
public class TraceLog3Impl implements TraceLogI3 {

    public String name = "hello";

    @Override
    public void log(String msg) {
        System.out.println("charge trace3: " + msg);
    }
}
