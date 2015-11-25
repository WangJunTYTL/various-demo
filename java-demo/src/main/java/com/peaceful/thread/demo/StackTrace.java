package com.peaceful.thread.demo;

/**
 * 调用线程堆栈
 *
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/11/10
 * @since 1.6
 */

public class StackTrace {

    public static void getInfo() {
        String location = "";
        StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
        location = "类名：" + stacks[2].getClassName() + "\n函数名：" + stacks[2].getMethodName()
                + "\n文件名：" + stacks[2].getFileName() + "\n行号："
                + stacks[2].getLineNumber() + "";
        System.out.println(location);

        for(int i=0;i<stacks.length;i++){
            location = i+"  at "+stacks[i].getClassName() + "." + stacks[i].getMethodName()
                    + "(" + stacks[i].getFileName() + ":"
                    + stacks[i].getLineNumber() + ")";
            System.out.println(location);
        }
    }

    public static void main(String[] args) {
        getInfo();
    }
}
