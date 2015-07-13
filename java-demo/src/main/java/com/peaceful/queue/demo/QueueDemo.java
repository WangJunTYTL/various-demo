package com.peaceful.queue.demo;

import com.peaceful.common.util.Util;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/7/13
 * @since 1.6
 */

public class QueueDemo {

    /**
     * 两大队列： ConcurrentLinkedDeque  BlockingDeque
     * @param args
     */
    public static void main(String[] args) {
        //性能高
        Queue<String> queue = new ConcurrentLinkedDeque<String>();
        queue.add("1");
        queue.add("2");
        Util.report(queue.poll());
        Util.report(queue.poll());

        Queue<String> blockQueue = new LinkedBlockingDeque<String>(2);
        blockQueue.add("1");
        blockQueue.add("2");
        Util.report(blockQueue.offer("3"));
        Util.report(blockQueue.poll());
        Util.report(blockQueue.poll());


    }
}
