package com.peaceful.thread.demo;

import com.peaceful.common.util.Util;

import java.util.concurrent.Callable;

/**
 * Created by wangjun on 15/2/5.
 */
public class Client implements Callable<String> {


    @Override
    public String call() throws Exception {
        int i = 0;
        StringBuffer stringBuffer = new StringBuffer();
        while (i < 97) {
            stringBuffer.append((char) i);
            Thread.sleep(200);
            i++;
            Util.report(i);
        }
        return stringBuffer.toString();
    }
}


