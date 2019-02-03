package com.peaceful.thrift.demo;

/**
 * Created by Jun on 15/12/5.
 */

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import service.demo.HelloServer;


public class HelloServiceClient1_2 {
    /**
     * 调用 Hello 服务
     */
    public static void main(String[] args) {
        try {
            TTransport transport = new TSocket("localhost", 9090);
            transport.open();

            TProtocol protocol = new TBinaryProtocol(transport);

            //TMultiplexedProtocol mp1 = new TMultiplexedProtocol(protocol, "HelloService"); // 指定远程服务名
            HelloServer.Iface client = new HelloServer.Client(protocol);

            // 单线程，串行处理
            for (int i = 0; i < 100; i++) {
                System.out.println(client.helloString("23"));

            }

        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }
    }
}