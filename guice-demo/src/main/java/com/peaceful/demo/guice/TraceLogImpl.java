package com.peaceful.demo.guice;

import com.google.inject.Singleton;
import org.slf4j.helpers.Util;

/**
 * Created by wangjun on 16/2/19.
 */
@Singleton
public class TraceLogImpl implements TraceLogI {

    private String scope = "init";

    @Override
    public void log(String msg) {
        System.out.println("charge trace1: " + msg);
    }

    @Override
    public void testScope() {
        Util.report(scope);
    }

    @Override
    public void setScope(String scope) {
        this.scope = scope;
    }
}
