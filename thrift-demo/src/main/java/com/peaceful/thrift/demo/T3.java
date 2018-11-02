package com.peaceful.thrift.demo;

import java.util.concurrent.TimeUnit;

/**
 * Created by Jun on 2018/9/11.
 */
public class T3 {

    public static void main(String[] args) throws InterruptedException {
        for (;;) {
            long startTime = System.currentTimeMillis();
            long timeSlice = TimeUnit.SECONDS.toMillis(30);
            long value = ((startTime / timeSlice) * timeSlice) + timeSlice;
            System.out.println(startTime+"--"+value);
            TimeUnit.SECONDS.sleep(1);

        }
    }
}
