package com.peaceful.manage.demo;

import java.lang.management.*;
import java.util.List;

/**
 * Created by wangjun on 16/1/21.
 */
public class MemoryMxBean {

    public static void main(String[] args) {

        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage memoryUsage = memoryMXBean.getHeapMemoryUsage();
        System.out.println("HeapMemoryUsage");
        System.out.println("----------------------");
        System.out.println("Init:" + memoryUsage.getInit() + "\nMax:" + memoryUsage.getMax() + "\nUsed:" + memoryUsage.getUsed());
        System.out.println();
        memoryUsage = memoryMXBean.getNonHeapMemoryUsage();
        System.out.println("NonHeapMemoryUsage");
        System.out.println("----------------------");
        System.out.println("Init:" + memoryUsage.getInit() + "\nMax:" + memoryUsage.getMax() + "\nUsed:" + memoryUsage.getUsed());
        System.out.println();
        List<GarbageCollectorMXBean> garbageCollectorMXBeanList = ManagementFactory.getGarbageCollectorMXBeans();
        System.out.println("GC");
        System.out.println("----------------------");
        for (GarbageCollectorMXBean gc : garbageCollectorMXBeanList) {
            System.out.println(gc.getName() + "\tTotal Count:" + gc.getCollectionCount() + "\tTotal Time:" + gc.getCollectionTime());
        }
        System.out.println();
        System.out.println("OS");
        System.out.println("----------------------");
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        System.out.println(operatingSystemMXBean.getName()+"\t"+operatingSystemMXBean.getArch()+"\t"+operatingSystemMXBean.getVersion()+
        "\t"+operatingSystemMXBean.getAvailableProcessors()+"\t"+operatingSystemMXBean.getSystemLoadAverage());

    }
}
