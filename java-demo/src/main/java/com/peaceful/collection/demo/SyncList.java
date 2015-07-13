package com.peaceful.collection.demo;


import com.peaceful.common.util.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/7/13
 * @since 1.6
 */

public class SyncList {

    public static void main(String[] args) {
        Vector vector = new Vector(6);
        vector.add("1");
        vector.add("2");

        List<String> list = new CopyOnWriteArrayList<String>();
        list.add("1");
        list.add("2");

        List<String> async = new ArrayList<String>();
        async = Collections.synchronizedList(async);
        async.add("1");
        async.add("2");
        Util.report("线程安全集合：vector"+vector);
        Util.report("线程安全集合：CopyOnWriteArrayList"+list);
        Util.report("线程安全集合：Collections.synchronizedList"+async);
    }
}
