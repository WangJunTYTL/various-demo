package com.peaceful.thread.demo;

import com.peaceful.common.util.Util;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/20
 * @since 1.6
 */

public class WaitMethodDemo {


    public static void main(String[] args) throws InterruptedException {
        Thread input = new Thread(new Input(), "input");
        Thread out = new Thread(new Out(), "out");
        out.start();
        input.start();
    }

    static class Out implements Runnable {

        @Override
        public void run() {
            Util.report("out");
        }
    }

    static class Input implements Runnable {

        @Override
        public void run() {
            for (; ; ) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                out.notifyAll();
                Util.report("input");
            }

        }
    }


}
