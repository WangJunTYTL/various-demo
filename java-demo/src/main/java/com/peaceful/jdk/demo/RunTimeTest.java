package com.peaceful.jdk.demo;

import com.peaceful.common.util.Util;

/**
 * Created by wangjun on 14/11/30.
 */
public class RunTimeTest {

    public static void main(String[] args) throws InterruptedException {
        Util.report("exe...");
        Thread.sleep(100000);
    }

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() { //jvm将要关闭时调用，例如通过system.exit(int)或^c
            public void run() {
                Util.report("shutdown...");
            }
        }, "ShutdownHook"));
    }
}
