package com.peaceful.thread.demo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 可调度的并发任务
 * Created by wangjun on 15/2/6.
 */
public class ExecutorsDemo2 {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.schedule(new Client(1),10, TimeUnit.SECONDS);
    }
}
