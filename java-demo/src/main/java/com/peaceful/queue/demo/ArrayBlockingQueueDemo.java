package com.peaceful.queue.demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/10/22
 * @since 1.6
 */

public class ArrayBlockingQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<String> blockQueue = new ArrayBlockingQueue<String>(6);
    }
}
