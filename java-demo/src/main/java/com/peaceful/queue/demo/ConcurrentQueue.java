package com.peaceful.queue.demo;

import com.peaceful.Util;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/7/13
 * @since 1.6
 */

public class ConcurrentQueue {

    public static void main(String[] args) {
        //性能高,适用于高并发场景下的队列
        Queue<String> queue = new ConcurrentLinkedDeque<String>();
        queue.add("1"); //插入失败，返回false
        queue.add("2");
        Util.report(queue.peek()); // 返回队列的头部元素
        Util.report(queue.poll());
        Util.report(queue.offer("3"));//添加一个元素并返回true 如果队列已满，则返回false
        Util.report(queue.poll());
    }
}
