package com.peaceful.collection.demo;

import com.peaceful.Util;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/7/13
 * @since 1.6
 */

public class SyncSet {

    public static void main(String[] args) {
        Set<String> set = new HashSet<String>();
        set = Collections.synchronizedSet(set);
        set.add("1");
        set.add("2");
        Util.report("线程安全集合： Collections.synchronizedSet" + set);

    }
}
