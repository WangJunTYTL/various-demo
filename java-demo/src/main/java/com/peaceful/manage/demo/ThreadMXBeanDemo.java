package com.peaceful.manage.demo;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;

/**
 * Created by wangjun on 16/1/21.
 */
public class ThreadMXBeanDemo {


    public static void main(String[] args) {
        java.lang.management.ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

        long[] threadIds = threadMXBean.getAllThreadIds();

        for (long id : threadIds) {
            ThreadInfo threadInfo = threadMXBean.getThreadInfo(id);
            System.out.println("ThreadId:" + id + threadInfo.getThreadName() + "\nCpuTime:" + threadMXBean.getThreadCpuTime(id) + "\nUserTime:" + threadMXBean.getThreadUserTime(id));
            System.out.println();
        }
    }
}
