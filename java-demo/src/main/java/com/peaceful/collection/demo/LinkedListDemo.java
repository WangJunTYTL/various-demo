package com.peaceful.collection.demo;

import com.alibaba.fastjson.JSON;
import com.peaceful.common.util.Util;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by wangjun on 16/2/22.
 */
public class LinkedListDemo {

    public static void main(String[] args) {
        List<String> link = new LinkedList<String>();
        link.add("a");
        link.add("b");
        link.add("c");
        link.add("d");

        Util.report(JSON.toJSONString(link));
    }
}
