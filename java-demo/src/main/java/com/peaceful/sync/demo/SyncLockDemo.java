package com.peaceful.sync.demo;

/**
 * Created by JunWang on 2016/12/30.
 */
public class SyncLockDemo {


    public static void main(String[] args) {
        Object lock = new Object();
        synchronized (lock){
            System.out.print("hello ");
            synchronized (lock){
                System.out.println("world ");
            }
        }
    }
}
