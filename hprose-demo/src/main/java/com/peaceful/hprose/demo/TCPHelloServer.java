package com.peaceful.hprose.demo;

import hprose.server.HproseTcpServer;

import static java.lang.Thread.*;

/**
 * Created by wang on 2017/2/21.
 */
public class TCPHelloServer {

    public static String hello(String name) {
        throw new RuntimeException("this is a error!");
    }
    public static void main(String[] args) throws Exception {
        HproseTcpServer server = new HproseTcpServer("tcp://localhost:4321");
        server.add("hello", TCPHelloServer.class);
        server.start();
        System.out.println("START");
    }
}
