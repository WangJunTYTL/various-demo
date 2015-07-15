package com.peaceful.collection.demo;

import com.peaceful.common.util.Util;

import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/7/13
 * @since 1.6
 */

public class BlockQueue {

    public static void main(String[] args) throws InterruptedException {
        // 阻塞队列，典型的生产者和消费者模式
        BlockingDeque<String> blockQueue = new LinkedBlockingDeque<String>(2);
        blockQueue.add("1");
        blockQueue.add("2");
        Util.report(blockQueue.offer("3"));
//        blockQueue.put("3");
        Util.report(blockQueue.take()); // 移除并返回队列头部的元素     如果队列为空，则阻塞
        Util.report(blockQueue.poll());
        Util.report(blockQueue.poll());

        Queue<String> blockPriorityQueue = new PriorityBlockingQueue<String>();
        blockPriorityQueue.offer("2"); // 优先级通过存入对象的compare方法
        blockPriorityQueue.offer("1");
        Util.report(blockPriorityQueue.poll());
    }
}
