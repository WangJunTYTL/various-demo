package com.peaceful.jdk.demo;

import com.peaceful.common.util.Util;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author wangjun
 * @version 1.0
 * @since 15/4/21.
 */

public class ReferenceDemo {

    /**
     * Java 四种引用类型
     *
     * @param args
     */
    public static void main(String[] args) {
        StringBuffer strbuf = new StringBuffer("hello world");//强引用
        StringBuffer strbuf1 = new StringBuffer("hello world");
        StringBuffer strbuf2 = new StringBuffer("hello world");
        //软引用
        SoftReference<StringBuffer> softReference = new SoftReference<StringBuffer>(strbuf);
        //弱引用
        WeakReference<StringBuffer> weakReference = new WeakReference<StringBuffer>(strbuf1);
        //虚引用
        ReferenceQueue<StringBuffer> stringBufferReferenceQueue = new ReferenceQueue<StringBuffer>();
        PhantomReference<StringBuffer> phantomReference = new PhantomReference<StringBuffer>(strbuf2, stringBufferReferenceQueue);

        Util.report("强引用存在时");
        Util.dashed();
        Util.report(softReference.get());
        Util.report(weakReference.get());
        Util.report(phantomReference.get());
        Util.enter();

//        清除强引用
        strbuf = null;
        strbuf1 = null;
        strbuf2 = null;

        Util.report("强引用不存在时");
        Util.dashed();
        Util.report(softReference.get());
        Util.report(weakReference.get());
        Util.report(phantomReference.get());
        Util.enter();

        Util.report("第一次gc后，弱引用会被回收");
        Util.dashed();
        System.gc();
        Util.report(softReference.get()); // 软引用会在内容紧张时立马回收
        Util.report(weakReference.get());// 弱引用会在gc时立马回收
        Util.report(phantomReference.get());//任何时候都不可以通过get()获得
        Util.enter();
    }
}
