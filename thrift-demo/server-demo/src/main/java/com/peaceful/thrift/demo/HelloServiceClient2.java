package com.peaceful.thrift.demo;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TTransport;

import java.util.concurrent.TimeUnit;

import service.demo.HelloServer;

/**
 * Created by Jun on 2018/11/29.
 */
public class HelloServiceClient2 {

    public static void main(String[] args) throws TException, InterruptedException {
        TTransport transport = new THttpClient("http://127.0.0.1:8787/TServer");

        TProtocol protocol = new TBinaryProtocol(transport);
        HelloServer.Client client = new HelloServer.Client(protocol);

        System.out.println(client.helloString("123"));

        TimeUnit.SECONDS.sleep(5);



    }
}
