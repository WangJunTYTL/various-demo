package com.peaceful.guava.demo;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ServiceManager;

/**
 * Created by wangjun on 16/8/5.
 */
public class ServiceManageDemo {

    public static void main(String[] args) {
        ScheduledServiceDemo scheduledServiceDemo =  new ScheduledServiceDemo();
        ServiceManager manager = new ServiceManager(Lists.newArrayList(scheduledServiceDemo));
        System.out.println(manager.isHealthy());
        manager.startAsync();
        manager.awaitHealthy();
        System.out.println(manager);
    }
}
