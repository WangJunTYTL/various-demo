package com.peaceful.guava.demo;

import com.google.common.eventbus.Subscribe;

/**
 * Created by wangjun on 16/8/5.
 */
public class EventListener {

    @Subscribe
    public void listen(EventMessage msg){
        System.out.println(msg.getMsg());
    }
}
