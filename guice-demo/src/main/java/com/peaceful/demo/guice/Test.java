package com.peaceful.demo.guice;

import com.google.inject.Inject;

/**
 * Created by wangjun on 16/2/20.
 */
public class Test {

    TraceLogI4 traceLogI4;

    @Inject
    TraceLogI4 traceLogI401;

    @Inject
    public void log(TraceLogI4 traceLogI4){
        this.traceLogI4 = traceLogI4;
    }

    public void hello(String str){
        traceLogI4.log(str);
        traceLogI401.log(str);
    }
}
