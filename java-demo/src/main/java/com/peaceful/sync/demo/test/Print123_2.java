package com.peaceful.sync.demo.test;

import java.util.concurrent.Semaphore;

/**
 * Created by JunWang on 2016/11/19.
 */
public class Print123_2 {

    private static Semaphore semaphoreA = new Semaphore(1);
    private static Semaphore semaphoreB = new Semaphore(0);

    public static void main(String[] args) throws InterruptedException {
        new Print(1,true).start();
        new Print(2,false).start();
    }

    private static class Print extends Thread {

        private int num;
        private boolean flag;

        public Print(int num, boolean flag) {
            this.num = num;
            this.flag = flag;
        }

        @Override
        public void run() {
            while (num < 101) {
                try {
                    if (flag) {
                        semaphoreA.acquire(1);
                        semaphoreB.release(0);
                    } else {
                        semaphoreB.acquire(1);
                        semaphoreA.release(0);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(num+",");
                num+=2;
                if (flag){
                    semaphoreB.release(1);
                }else {
                    semaphoreA.release(1);
                }
            }
        }
    }
}
