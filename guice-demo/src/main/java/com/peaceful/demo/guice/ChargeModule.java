package com.peaceful.demo.guice;

import com.google.inject.AbstractModule;

/**
 * Created by wangjun on 16/2/19.
 */
public class ChargeModule extends AbstractModule{


    @Override
    protected void configure() {
        bind(TraceLogI.class).to(TraceLogImpl.class);
    }
}
