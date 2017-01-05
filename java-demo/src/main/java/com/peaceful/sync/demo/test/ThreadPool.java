package com.peaceful.sync.demo.test;

import com.peaceful.thread.demo.thread.pool.BlockQueue;

import java.util.concurrent.TimeUnit;

/**
 * Created by JunWang on 2016/12/14.
 */
public class ThreadPool {


    public static class BolckQueue<T> {

        private Object lock = new Object();
        private Node head;
        private Node tail;

        public void put(T v) {
            synchronized (lock) {
                if (head == null) {
                    head = new Node(v);
                    tail = head;
                } else if (tail == head) {
                    tail = new Node(v);
                    head.next = tail;
                } else {
                    tail.next = new Node(v);
                }
                lock.notifyAll();
            }
        }

        public T poll() throws InterruptedException {
            while (true) {
                synchronized (lock) {
                    if (head != null) {
                        if (head == tail) {
                            T v = head.v;
                            head = null;
                            tail = null;
                            lock.notifyAll();
                            return v;
                        } else {
                            T v = head.v;
                            head = head.next;
                            lock.notifyAll();
                            return v;
                        }
                    }else {
                        lock.wait(); // 此时等待
                    }
                }
            }
        }

        public class Node {
            T v;
            Node next;

            public Node(T v) {
                this.v = v;
            }
        }
    }

    public static class Pool {

        private static int size;
        private static BlockQueue<Runnable> tasks;

        public Pool(int size) {
            this.size = size;
        }

        public static void start() {
            for (int i = 0; i < size; i++) {
                new Thread() {
                    @Override
                    public void run() {
                        while (true) {
                            try {
                                Runnable runnable = tasks.get();
                                if (runnable != null)
                                    runnable.run();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Pool.size  =2;
        Pool.tasks = new BlockQueue<Runnable>(6);
        Pool.start();
        for (;;){
            Pool.tasks.put(new Task());
        }
    }

    private static class Task implements Runnable{
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Hello");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}

