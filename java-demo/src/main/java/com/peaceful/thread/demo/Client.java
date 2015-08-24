package com.peaceful.thread.demo;

import com.peaceful.common.util.Util;

import java.util.concurrent.Callable;

/**
 * 模拟一个请求需要消耗时间长的动作
 * <p/>
 * Created by wangjun on 15/2/5.
 */
public class Client implements Callable<String> {

    int start;
    public Client(int start){
        this.start = start;
    }

    @Override
    public String call() throws Exception {
        int i = 0;
        StringBuffer stringBuffer = new StringBuffer();
        while (i < 97) {
            stringBuffer.append((char) (i + start));
            Thread.sleep(100);
            i++;
//            Util.report(i+start);
        }
        Thread.sleep(10000000);
        return stringBuffer.toString();
    }
}


