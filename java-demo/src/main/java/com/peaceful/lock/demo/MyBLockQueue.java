package com.peaceful.lock.demo;

import com.peaceful.Util;

import java.util.concurrent.locks.*;

/**
 * Created by wangjun on 16/2/26.
 */
public class MyBLockQueue {

    public static void main(String[] args) {
        // 利用sync关键字实现
//        BlockQueueI blockQueue = new BlockQueue(6);
        // 利用reentrantLock
        BlockQueueI blockQueue = new BlockQueue2(6);
        for (int i = 0; i < 10000; i++) {
            new Writer(blockQueue).start();
            new Reader(blockQueue).start();
        }
    }
}

class Writer extends Thread {

    BlockQueueI blockQueue ;

    public Writer(BlockQueueI blockQueue) {
        this.blockQueue = blockQueue;
    }

    @Override
    public void run() {
        try {
            int x = (int) (Math.random() * 100);
            blockQueue.put(x);
            Util.report("put-> " + x);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Reader extends Thread {

    BlockQueueI blockQueue;

    public Reader(BlockQueueI blockQueue) {
        this.blockQueue = blockQueue;
    }

    @Override
    public void run() {
        try {
            Integer x = blockQueue.poll();
            Util.report("poll->" + x);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

interface BlockQueueI{

    void put(int data) throws InterruptedException ;
    Integer poll() throws InterruptedException ;
}

class BlockQueue implements BlockQueueI{
    private LinkedNodes linkedNodes = null;
    private int size = 0;

    public BlockQueue(int size) {
        if (size <= 0) {
            throw new RuntimeException("size must gt 0");
        }
        this.size = size;
        linkedNodes = new LinkedNodes(size);
    }

    public synchronized void put(int data) throws InterruptedException {
        if (linkedNodes.getLength() < size) {
            linkedNodes.put(data);
            notifyAll();
        } else {
            Util.report("block put...");
            wait();
            Util.report("open block put...");
            put(data);
        }
    }

    public synchronized Integer poll() throws InterruptedException {
        if (linkedNodes.getLength() > 0) {
            Integer r = linkedNodes.poll();
            notifyAll();
            return r;
        } else {
            Util.report("block poll...");
            wait();
            Util.report("open block poll...");
        }
        return poll();
    }
}

class BlockQueue2 implements BlockQueueI{
    private LinkedNodes linkedNodes = null;
    private int size = 0;

    Lock lock = new ReentrantLock();
    Condition notFull = lock.newCondition();
    Condition notEmpty = lock.newCondition();


    public BlockQueue2(int size) {
        if (size <= 0) {
            throw new RuntimeException("size must gt 0");
        }
        this.size = size;
        linkedNodes = new LinkedNodes(size);
    }

    public void put(int data) throws InterruptedException {
        lock.lock();
        try {
            if (linkedNodes.getLength() < size) {
                linkedNodes.put(data);
                notEmpty.signal();
            } else {
                Util.report("block put...");
                notFull.await();
                Util.report("open block put...");
                put(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public synchronized Integer poll() throws InterruptedException {
        lock.lock();
        try {
            if (linkedNodes.getLength() > 0) {
                Integer r = linkedNodes.poll();
                notFull.signal();
                return r;
            } else {
                Util.report("block poll...");
                notEmpty.await();
                Util.report("open block poll...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return poll();
    }
}

class LinkedNodes {

    private int size;
    private int length;


    private Node root;
    private Node tail;

    public LinkedNodes(int size) {
        this.size = size;
    }

    public void put(int data) {
        if ((getLength() + 1) > size) {
            throw new RuntimeException("LinkedNodes max size is  " + size);
        }
        Node node = new Node();
        node.data = data;
        if (root == null && tail == null) {
            root = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        length++;
    }

    public Integer poll() {
        if (root != null) {
            Node r = new Node();
            r.data = root.data;

            if (root.next != null) {
                root = root.next;
            } else {
                root = tail = null;
            }
            length--;
            return r.data;
        } else {
            return null;
        }
    }

    public int getLength() {
        return length;
    }

}

class Node {

    int data;
    Node next;
}
