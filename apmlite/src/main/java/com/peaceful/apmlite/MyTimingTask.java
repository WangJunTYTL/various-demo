package com.peaceful.apmlite;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangjun on 2019-02-02.
 */
public abstract class MyTimingTask {

    private static ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    public static void setUp(MyRDB rdb) {
        scheduledExecutorService.scheduleWithFixedDelay(new MonitorForJVM(rdb), 1, 1, TimeUnit.MINUTES);
    }

}
