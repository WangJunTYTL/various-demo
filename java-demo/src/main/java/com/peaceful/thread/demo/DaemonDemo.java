package com.peaceful.thread.demo;

import com.peaceful.common.util.Util;

/**
 * daemon 线程
 * <p/>
 * daemon称之为后台线程,当JVM发现没有前台线程活动时会立马终止后台线程的运行
 *
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/10/22
 * @since 1.6
 */

public class DaemonDemo {


    public static void main(String[] args) throws InterruptedException {
        Worker worker = new Worker();
        // 当程序中没有活动的前台线程时，后台线程会被jvm中断
        worker.setDaemon(true);// 设置为后台线程 ，测试时你会发现程序会立马退出，而注释掉程序会在worker线程执行完毕后退出
        worker.start();
    }


    static class Worker extends Thread {
        @Override
        public void run() {
            try {
                sleep(6000);
                Util.report("hello world");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
