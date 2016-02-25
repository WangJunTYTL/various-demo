package com.peaceful.thread.demo;

/**
 * @author <a href="mailto:wangjuntytl@163.com">WangJun</a>
 * @version 1.0 15/12/28
 */
public class T1 {

    private static boolean flag = true;
//    volatile

    public static void main(String[] args) throws InterruptedException {

        Worker worker = new Worker();
        worker.start();
        flag = false;

    }


    private static class Worker extends Thread {

        @Override
        public void run() {
            while (flag) {
                yield();
            }
            System.out.println("hello world");
        }
    }
}
