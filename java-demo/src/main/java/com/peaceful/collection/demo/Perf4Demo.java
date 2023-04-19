package com.peaceful.collection.demo;

import com.peaceful.Util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/7/28
 * @since 1.6
 */

public class Perf4Demo {

    static BlockingQueue<String> blockQueue = new ArrayBlockingQueue<String>(6);

    public static void append(String message) {
        Util.report("offer message " + blockQueue.offer(message));
    }

    public static void parse() {
        try {
            Util.report("parse message " + blockQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class WT extends Thread {
        @Override
        public void run() {
            while (true) {
                append("message id " + Math.random() * 10);
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class RT extends Thread {
        @Override
        public void run() {
            while (true)
                parse();
        }
    }

    public static void main(String[] args) {
        Thread w = new Thread(new WT());
        Thread r = new Thread(new RT());

        w.start();
        r.start();
    }


}
