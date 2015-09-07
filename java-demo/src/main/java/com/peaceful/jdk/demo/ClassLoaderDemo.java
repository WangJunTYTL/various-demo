package com.peaceful.jdk.demo;

import com.peaceful.common.util.Util;
import sun.reflect.Reflection;

import java.lang.ClassLoader;

/**
 * Created by wangjun on 15/2/21.
 */
public class ClassLoaderDemo {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Util.report(Reflection.getCallerClass());
        /** 载入ClassLoaderTest的classloader **/
        Util.report("载入ClassLoaderTest的classloader->" + ClassLoaderDemo.class.getClassLoader());
        /** appache 提供的classloader */
        com.sun.org.apache.bcel.internal.util.ClassLoader classLoader = new com.sun.org.apache.bcel.internal.util.ClassLoader(java.lang.ClassLoader.getSystemClassLoader());
        Util.report("appache 提供的classloader->" + classLoader);
        Class ClassLoaderTestClass = Class.forName("com.peaceful.jdk.demo.ClassLoaderTest", false, classLoader);
        Util.report("classloader ? ->" + ClassLoaderTestClass.getClassLoader());
        Util.report("apache class loader parent ->" + ClassLoaderTestClass.getClassLoader().getParent());
        /** java.lang.ClassCastException: com.peaceful.jdk.demo.ClassLoaderTest cannot be cast to com.peaceful.jdk.demo.ClassLoaderTest **/
//        ClassLoaderTest ClassLoaderTest = (ClassLoaderTest) ClassLoaderTestClass.newInstance();
        /** 不同的classload 载入同一个类不能进行比较和转换 */
        Util.report("? ->" + ClassLoaderTestClass.isInstance(ClassLoaderDemo.class));
        /** 通过指定classloader 载入指定类 **/
        classLoader.loadClass("com.peaceful.jdk.demo.ClassLoaderTest");
        /** 系统启动时设定的classloader **/
        Util.report(ClassLoader.getSystemClassLoader());

        int[] arr = {1, 2, 3, 4, 5};
        ClassLoaderDemo[] arrClassLoaderTest = {};
        /**<p> <tt>Class</tt> objects for array classes are not created by class
         * loaders, but are created automatically as required by the Java runtime.
         * The class loader for an array class, as returned by {@link
         * Class#getClassLoader()} is the same as the class loader for its element
         * type; if the element type is a primitive type, then the array class has no
         * class loader.
         * */
        Util.report(arr.getClass().getClassLoader());
        Util.report(arrClassLoaderTest.getClass().getClassLoader());
        /** 父classloader **/
        Util.report(ClassLoaderDemo.class.getClassLoader().getParent());
        /** 父classloader的classloader **/
        Util.report(ClassLoaderDemo.class.getClassLoader().getParent().getParent());
        Util.report(T2.class.getClassLoader());
        Util.report(System.currentTimeMillis());
        Util.report(System.nanoTime());
    }

    /**
     * 输出结果
     *
     P_LOG: class com.intellij.rt.execution.application.AppMain
     P_LOG: 载入ClassLoaderTest的classloader->sun.misc.Launcher$AppClassLoader@3479404a
     P_LOG: appache 提供的classloader->com.sun.org.apache.bcel.internal.util.ClassLoader@486f8860
     P_LOG: classloader ? ->com.sun.org.apache.bcel.internal.util.ClassLoader@486f8860
     P_LOG: apache class loader parent ->sun.misc.Launcher$AppClassLoader@3479404a
     P_LOG: ? ->false
     P_LOG: sun.misc.Launcher$AppClassLoader@3479404a
     P_LOG: null
     P_LOG: sun.misc.Launcher$AppClassLoader@3479404a
     P_LOG: sun.misc.Launcher$ExtClassLoader@46bd530
     P_LOG: null
     P_LOG: sun.misc.Launcher$AppClassLoader@3479404a
     P_LOG: 1424681861529
     P_LOG: 1424681861530021000
     *
     */

    class T2 {

    }


}
