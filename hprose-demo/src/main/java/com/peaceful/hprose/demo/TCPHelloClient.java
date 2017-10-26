package com.peaceful.hprose.demo;

import hprose.client.HproseTcpClient;

/**
 * Created by wang on 2017/2/21.
 */
interface IHello {
    String hello(String name);
}

public class TCPHelloClient {
    public static void main(String[] args) throws Throwable {
        System.out.println("START");
        HproseTcpClient client = new HproseTcpClient("tcp://localhost:4321");
        IHello helloClient = client.useService(IHello.class);
        System.out.println(helloClient.hello("World"));
        System.out.println("END");
    }
}