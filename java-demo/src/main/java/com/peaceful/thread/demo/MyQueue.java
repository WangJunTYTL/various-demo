package com.peaceful.thread.demo;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/20
 * @since 1.6
 */

public class MyQueue {

    public static Queue<String> queue= new ConcurrentLinkedDeque<String>();
}
