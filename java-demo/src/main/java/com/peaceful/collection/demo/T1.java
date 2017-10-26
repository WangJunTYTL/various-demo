package com.peaceful.collection.demo;

public class T1 {

    public static void main(String[] args) throws InterruptedException {
        int num = 0;
        boolean isStop = false;
        while (!isStop) {
            say("Hello" + "_" + num++);
        }
    }

    private static String say(String msg) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("msg:" + msg);
        return "Receive:" + msg;
    }
}

