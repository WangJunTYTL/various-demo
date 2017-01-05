package com.peaceful.sync.demo.test;

/**
 * Created by JunWang on 2016/12/14.
 */
public class Print123 {


    private static int num = 1;

    public static void main(String[] args) {

        new Print(true,new Object()).start();
        new Print(false,new Object()).start();
    }

    private static class Print extends Thread {

        public boolean flag; // true 打印基数
        public Object lock; // 同步锁

        public Print(boolean flag,Object lock){
            this.flag = flag;
            this.lock = lock;
        }

        @Override
        public void run() {
            while (num < 100    ) {
                synchronized (lock){
                    if (flag ){
                        if (num % 2 != 0){
                            System.out.print(num+" ");
                            num++;
                            lock.notifyAll();
                        }
                    }else {
                        if (num % 2 == 0){
                            System.out.print(num+" ");
                            num++;
                            lock.notifyAll();
                        }
                    }
                }
            }
        }
    }
}
