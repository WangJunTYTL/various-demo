package com.peaceful.thread.demo;

import com.peaceful.util.Util;

/**
 * Date 14/11/15.
 * Author WangJun
 * Email wangjuntytl@163.com
 */
public class ThreadTest  {

    public static void main(String[] args) {
        ThreadA a = new ThreadA();
        ThreadB b = new ThreadB();
        a.start();
        new Thread(b).start(); // 不可以直接调用run方法，因为Runnable只是一个普通的接口
    }
}


class ThreadA extends Thread {

    @Override
    public void run() {
        Util.report("extend thread");
    }
}

class  ThreadB implements Runnable{

    @Override
    public void run() {
        Util.report("implement runnable");
    }
}
