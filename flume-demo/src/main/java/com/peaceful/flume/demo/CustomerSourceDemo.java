package com.peaceful.flume.demo;

import org.apache.flume.Source;
import org.apache.flume.channel.ChannelProcessor;
import org.apache.flume.lifecycle.LifecycleState;

/**
 * 自定义flume source 部件,一般不直接实现Source,而是继承AbstractSource
 *
 * Created by wangjun on 15/12/3.
 */
public class CustomerSourceDemo implements Source {

    @Override
    public void setChannelProcessor(ChannelProcessor channelProcessor) {

    }

    @Override
    public ChannelProcessor getChannelProcessor() {
        return null;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public LifecycleState getLifecycleState() {
        return null;
    }

    @Override
    public void setName(String s) {

    }

    @Override
    public String getName() {
        return null;
    }
}
