package com.peaceful.apmlite;

import java.sql.Timestamp;
import java.util.Map;

/**
 * Created by wangjun on 2019-02-02.
 */
public class Metric11 {

    private String tag;
    private long heapMemory;
    private long nonHeapMemory;
    private long gcTime;
    private long gcCount;
    private long threadCount;
    private Timestamp createTime;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public long getHeapMemory() {
        return heapMemory;
    }

    public void setHeapMemory(long heapMemory) {
        this.heapMemory = heapMemory;
    }

    public long getNonHeapMemory() {
        return nonHeapMemory;
    }

    public void setNonHeapMemory(long nonHeapMemory) {
        this.nonHeapMemory = nonHeapMemory;
    }

    public long getGcTime() {
        return gcTime;
    }

    public void setGcTime(long gcTime) {
        this.gcTime = gcTime;
    }

    public long getGcCount() {
        return gcCount;
    }

    public void setGcCount(long gcCount) {
        this.gcCount = gcCount;
    }

    public long getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(long threadCount) {
        this.threadCount = threadCount;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public void toBean(Map<String, Object> date){
        setTag((String) date.get("tag"));
        setHeapMemory((Long) date.get("heap_memory"));
        setNonHeapMemory((Long) date.get("non_heap_memory"));
        setGcCount((Long) date.get("gc_count"));
        setGcTime((Long) date.get("gc_time"));
        setThreadCount((Long) date.get("thread_count"));
        setCreateTime((Timestamp) date.get("create_time"));
    }
}
