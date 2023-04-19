package com.peaceful.queue.demo;

import com.peaceful.Util;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/9/7
 * @since 1.6
 */

public class ConcurrentLinkedQueueDemo {

    public static void main(String[] args) {
        ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
        List<Integer> list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);

        concurrentLinkedQueue.addAll(list);

        // 返回头部
        concurrentLinkedQueue.peek();

        //返回头部，empty抛出异常
        concurrentLinkedQueue.element();

        concurrentLinkedQueue.offer(123);


        Util.report(concurrentLinkedQueue.poll());

        for (; ; ) {
            Object o = concurrentLinkedQueue.poll();
            if (o == null) break;
            Util.report(o);
        }
    }
}
