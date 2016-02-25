package com.peaceful.demo.guice;

import com.google.inject.Provider;

/**
 * Created by wangjun on 16/2/20.
 */
public class TraceLog3Provider implements Provider<TraceLogI3> {

    @Override
    public TraceLogI3 get() {
        return new TraceLog3Impl();
    }
}
