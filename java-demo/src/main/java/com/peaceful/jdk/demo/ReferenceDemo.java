package com.peaceful.jdk.demo;

import com.peaceful.common.util.Util;

import java.lang.ref.*;

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
    public static void main(String[] args) throws InterruptedException {
        StringBuffer b1 = new StringBuffer("hello world");//强引用

        //软引用
        SoftReference<StringBuffer> softReference = new SoftReference<StringBuffer>(new StringBuffer("hello world"));
        Util.report("软引用会在内存吃紧时，被回收");
        Util.dashed();
        System.gc();
        Util.report(softReference.get());
        Util.enter();


        //弱引用
        ReferenceQueue<StringBuffer> referenceQueue = new ReferenceQueue<StringBuffer>();
        ReferenceQueue<StringBuffer> referenceQueue2 = new ReferenceQueue<StringBuffer>();
        WeakReference<StringBuffer> weakReference = new WeakReference<StringBuffer>(new StringBuffer("hello world"), referenceQueue);
        WeakReference<StringBuffer> weakReference2 = new WeakReference<StringBuffer>(new StringBuffer("hello world"), referenceQueue2);
        StringBuffer buffer = weakReference.get();
        System.gc();
        Util.report("弱引用会在gc时被回收，");
        Util.dashed();
        Util.report(weakReference.get()); // 存在引用就不会被删除
        try {
            Util.report(weakReference2.get());//弱引用会在gc时立马回收
        } catch (Exception e) {
            e.printStackTrace();
        }



        // 为了避免内存泄露，把保存软引用对象的Reference也给清除掉，这个不是必须的，可以被gc自动清除
        Reference ref;
        while ((ref = referenceQueue.poll()) != null) {
            // 清除
        }

        Reference ref2;
        while ((ref2 = referenceQueue2.poll()) != null) {
            //
        }

        Util.enter();

        //虚引用
        ReferenceQueue<StringBuffer> stringBufferReferenceQueue = new ReferenceQueue<StringBuffer>();
        PhantomReference<StringBuffer> phantomReference = new PhantomReference<StringBuffer>(new StringBuffer("hello world"), stringBufferReferenceQueue);
        try {
            Util.report(phantomReference.get());//任何时候都不可以通过get()获得
        } catch (Exception e) {
            e.printStackTrace();
        }
        Util.enter();
    }
}
