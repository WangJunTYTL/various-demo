package com.peaceful.jdk.demo;

import com.peaceful.Util;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by wangjun on 15/2/28.
 */
public class ProcessDemo {

    public static void main(String[] args) throws IOException {
        Process process = Runtime.getRuntime().exec("pwd");
        StringBuffer cmdout = new StringBuffer();
        InputStream fis = process.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String line = null;
        while ((line = br.readLine()) != null) {
            cmdout.append(line).append(System.getProperty("line.separator"));
        }
        Util.report("执行系统命令后的结果为：\n" + cmdout.toString());
    }
}
