package com.peaceful.apmlite;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.ThreadMXBean;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangjun on 2019-02-02.
 */
public class MonitorForJVM implements Runnable {

    private static ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
    private static MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

    private MyRDB rdb;

    public MonitorForJVM(MyRDB rdb) {
        this.rdb = rdb;
    }

    @Override
    public void run() {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("tag", "jvm");
            MemoryUsage memoryUsage = memoryMXBean.getHeapMemoryUsage();
            int used = Integer.valueOf(String.valueOf(memoryUsage.getUsed() / 1024 / 1024));
            data.put("heap_memory", used);

            memoryUsage = memoryMXBean.getNonHeapMemoryUsage();
            used = Integer.valueOf(String.valueOf(memoryUsage.getUsed() / 1024 / 1024));
            data.put("non_heap_memory", used);

            long garbageCollectionTime = 0;
            int gcCount = 0;
            for (final GarbageCollectorMXBean garbageCollector : ManagementFactory
                    .getGarbageCollectorMXBeans()) {
                garbageCollectionTime += garbageCollector.getCollectionTime();
                gcCount += garbageCollector.getCollectionCount();
            }
            data.put("gc_time", garbageCollectionTime);
            data.put("gc_count", gcCount);
            data.put("thread_count", threadMXBean.getThreadCount());
            rdb.insertMetric(MetricNum.Metric_11, data);
            System.out.println("jvm----");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
