package com.peaceful.schedule;

import com.peaceful.common.util.Util;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/10/27
 * @since 1.6
 */

public class TimerDemo {

    public static void main(String[] args) {
        Timer timer = new Timer("testTimer",false);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Util.report("hello world");
            }
        }, 0L,5000);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Util.report("hello world");
            }
        }, 0L,5000);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Util.report("hello world");
            }
        },0L);
    }
}
