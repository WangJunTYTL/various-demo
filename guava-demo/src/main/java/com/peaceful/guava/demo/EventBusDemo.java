package com.peaceful.guava.demo;

import com.google.common.eventbus.EventBus;

/**
 * Created by wangjun on 16/8/5.
 */
public class EventBusDemo {

    public static void main(String[] args) {
        EventBus bus = new EventBus("Test");
        bus.register(new EventListener());
        bus.post(new EventMessage("hello world"));
    }
}
