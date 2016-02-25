package com.peaceful.manage.demo;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

/**
 * Created by wangjun on 16/1/21.
 */
public class MemoryMxBean {

    public static void main(String[] args) {

        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage memoryUsage = memoryMXBean.getHeapMemoryUsage();
        System.out.println("HeapMemoryUsage");
        System.out.println("----------------------");
        System.out.println("Init:"+memoryUsage.getInit()+"\nMax:"+memoryUsage.getMax()+"\nUsed:"+memoryUsage.getUsed());
        System.out.println();
        memoryUsage = memoryMXBean.getNonHeapMemoryUsage();
        System.out.println("NonHeapMemoryUsage");
        System.out.println("----------------------");
        System.out.println("Init:"+memoryUsage.getInit()+"\nMax:"+memoryUsage.getMax()+"\nUsed:"+memoryUsage.getUsed());
    }
}
