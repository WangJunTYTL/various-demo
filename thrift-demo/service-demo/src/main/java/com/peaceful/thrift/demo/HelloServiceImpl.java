package com.peaceful.thrift.demo;

/**
 * Created by Jun on 15/12/5.
 */
import org.apache.thrift.TException;

import java.util.concurrent.TimeUnit;

import service.demo.FirstRequest;
import service.demo.FirstResponse;
import service.demo.HelloServer;

public class HelloServiceImpl implements HelloServer.Iface {
    @Override
    public boolean helloBoolean(boolean para) throws TException {
        return para;
    }
    @Override
    public int helloInt(int para) throws TException {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(para));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        throw new RuntimeException("err");
        System.out.println("int->"+para);
        throw new RuntimeException();
//        return para;
    }
    @Override
    public String helloNull() throws TException {
        return null;
    }

    @Override
    public FirstResponse request(FirstRequest request) throws TException {
        return null;
    }

    @Override
    public String helloString(String para) throws TException {
        try {
            System.err.println(para);
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return para;
    }
    @Override
    public void helloVoid() throws TException {
        System.out.println("Hello World");
    }
}