package com.peaceful.guava.demo;

import com.google.common.util.concurrent.AbstractScheduledService;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangjun on 16/8/5.
 */
public class ScheduledServiceDemo extends AbstractScheduledService{

    @Override
    protected void runOneIteration() throws Exception {
        System.out.println("hello world!");
    }

    @Override
    protected Scheduler scheduler() {
        return Scheduler.newFixedDelaySchedule(5,5, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        ScheduledServiceDemo demo = new ScheduledServiceDemo();
        demo.startAsync();
        System.out.println(demo.toString());
    }
}
