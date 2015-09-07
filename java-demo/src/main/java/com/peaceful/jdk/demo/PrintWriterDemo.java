package com.peaceful.jdk.demo;


import com.peaceful.common.util.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Date 14/11/5.
 * Author WangJun
 * Email wangjuntytl@163.com
 */
public class PrintWriterDemo {

    public static void main(String[] args) throws FileNotFoundException {
        String targrtDir = System.getProperty("user.dir", "<NA>") + "/temp.txt";
        Util.report(targrtDir);
        PrintWriter printWriter = new PrintWriter(new File(targrtDir));
        printWriter.write("###############################################\n");
        printWriter.write("测试PrintWriter,写入数据\n");
        printWriter.write("hello world\n");
        printWriter.flush();
        printWriter.close();
    }
}
