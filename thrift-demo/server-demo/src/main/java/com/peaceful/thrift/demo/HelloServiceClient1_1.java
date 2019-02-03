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


public class HelloServiceClient1_1 {
    /**
     * 调用 Hello 服务
     */
    public static void main(String[] args) {
        try {
            TTransport transport = new TSocket("localhost", 9090);
            transport.open();

            TProtocol protocol = new TBinaryProtocol(transport);

            HelloServer.Client client = new HelloServer.Client(protocol);

            // 测试并发，这里并发肯定是有问题的，同一个socket在多个线程中数据读取会发生错乱
            // 生成环境 可以采用池技术 或者采用短连接，每次调用打开新的连接
            for (int i=0;i<100;i++ ) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.print(client.helloString("23"));
                        } catch (TException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }

        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }
    }
}