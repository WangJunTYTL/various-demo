package com.peaceful.demo.guice;

/**
 * Created by wangjun on 16/2/19.
 */
public interface TraceLogI {

    void log(String msg);

    void testScope();

    void setScope(String scope);
}
