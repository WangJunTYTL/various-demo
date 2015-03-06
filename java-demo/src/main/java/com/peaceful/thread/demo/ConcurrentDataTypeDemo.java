package com.peaceful.thread.demo;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 并发数据结构
 * <p/>
 * Created by wangjun on 15/2/11.
 */
public class ConcurrentDataTypeDemo {

    public static void main(String[] args) {
        //Collections.synchronizedList 主要操作是把涉及到集合操作的方法加上同步关键字：synchronize，注意当遍历的时候需要
        // It is imperative that the user manually synchronize on the returned
        // list when iterating over it:
        Collections.synchronizedList(new ArrayList<Object>());

        //在写的时候，复制一个集合的副本进行操作，然后再合并，get时不加锁
        List<String> list = new CopyOnWriteArrayList<String>();

        //读写时都加锁，与CopyOnWriteArrayList相比，写频繁时用vector，读频繁时用CopyOnWriteArrayList
        List<String> vector = new Vector<String>();


    }
}
