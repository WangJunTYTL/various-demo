package com.peaceful.jdk.demo;

import com.peaceful.common.util.Util;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * <a mailto:wangjuntytl@163.com>Email:wangjuntytl@163.com</a>
 *
 * @author wangjun
 * @version 1.0
 * @since 15/4/12.
 */

public class T3 {

    public static void main(String[] args) {

        Util.report("test softReference");
        Util.dashed();
        MapKey mapKey = new MapKey(1);
        ReferenceQueue<Object> queue = new ReferenceQueue<Object>();
        SoftReference<MapKey> mapKeySoftReference = new SoftReference<MapKey>(mapKey, queue);
        mapKey = null;
        Util.report(mapKeySoftReference.get());
        System.gc();
        Util.report(mapKeySoftReference.get());
        Util.enter();

        Util.report("test weakReference");
        Util.dashed();
        MapKey mapKey2 = new MapKey(1);
        ReferenceQueue<Object> queue2 = new ReferenceQueue<Object>();
        WeakReference<MapKey> weakReference = new WeakReference<MapKey>(mapKey2, queue2);
        mapKey2 = null;
        Util.report(weakReference.get());
        System.gc();
        Util.report(weakReference.get());
    }
}
