package com.peaceful.thread.demo.thread.pool;

/**
 * Created by JunWang on 2016/12/9.
 */
public class BlockQueue<T> {

    private Object lock = new Object();

    private int size;
    private int currentSize;

    private Node head = null;
    private Node tail = null;

    public BlockQueue(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("size must > 0 ");
        }
        this.size = size;
    }

    public void put(T v) throws InterruptedException {
        synchronized (lock) {
            if (currentSize >= size){
                lock.wait();
            }
            currentSize++;
            if (head == null) {
                head = new Node(v);
                head.next = tail;
            } else {
                if (tail == null) {
                    tail = new Node(v);
                } else {
                    tail.next = new Node(v);
                }
            }
            System.out.println("put element:"+v);
            lock.notifyAll();
        }
    }

    public T get() throws InterruptedException {
        while (true) {
            synchronized (lock) {
                if (head == null) {
                    lock.wait();
                    continue;
                } else {
                    Node tmp = head;
                    head = head.next;
                    currentSize--;
                    lock.notifyAll();
                    return (T) tmp.v;
                }
            }
        }
    }

    private class Node<T> {
        public T v;
        public Node next;

        public Node(T v) {
            this.v = v;
        }

    }
}
