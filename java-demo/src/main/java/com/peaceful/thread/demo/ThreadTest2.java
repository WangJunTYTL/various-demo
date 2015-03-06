package com.peaceful.thread.demo;

import com.peaceful.common.util.Util;

/**
 * Created by wangjun on 15/2/23.
 */
public class ThreadTest2 {

    public static void main(String[] args) {
        String name = Thread.currentThread().getName();
        Util.report(name);
//        Thread.currentThread().setDaemon(true); //只有在没有启动时设置
        DaemonThread daemonThread = new DaemonThread();
        daemonThread.setDaemon(true);
        daemonThread.start();
//        daemonThread.dumpStack();
        Util.report(daemonThread.toString());

    }


}

class DaemonThread extends Thread {

    @Override
    public void run() {
        Util.report("hello world");
    }
}
