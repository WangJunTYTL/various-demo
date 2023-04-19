package com.peaceful.queue.demo;

import com.peaceful.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/7/13
 * @since 1.6
 */

public class LinkedBlockingDequeDemo {

    public static void main(String[] args) throws InterruptedException {
        // 阻塞队列，典型的生产者和消费者模式
        BlockingDeque<String> blockQueue = new LinkedBlockingDeque<String>(2);
        blockQueue.add("1");
        blockQueue.add("2");
        Util.report(blockQueue.offer("3")); // 假如已经满了直接返回false
//        blockQueue.put("3"); // 假如满了,block
        Util.report(blockQueue.take()); // 移除并返回队列头部的元素     如果队列为空，则阻塞
        Util.report(blockQueue.poll());
        Util.report(blockQueue.poll()); // 如果去不到返回null

        blockQueue.offer("!");
        blockQueue.offer("!");
        blockQueue.offer("!");

//        将数据drain到其它集合
        List<String> data = new ArrayList<String>();
        blockQueue.drainTo(data, 6);
        Util.report(data);


        Queue<String> blockPriorityQueue = new PriorityBlockingQueue<String>();
        blockPriorityQueue.offer("2"); // 优先级通过存入对象的compare方法
        blockPriorityQueue.offer("1");
        Util.report(blockPriorityQueue.poll());
    }
}
