package com.peaceful.thrift.demo;

/**
 * Created by Jun on 15/12/5.
 */

import org.apache.thrift.TProcessor;
import org.apache.thrift.server.AbstractNonblockingServer;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

import service.demo.HelloServer;


public class HelloServiceServer3 {
    /**
     * 启动 Thrift 服务器
     */
    public static void main(String[] args) {
        try {
            // 注册服务实现类，实现类必须要实现iface接口
            TProcessor hello = new HelloServer.Processor(new HelloServiceImpl());

            TNonblockingServerSocket serverTransport = new TNonblockingServerSocket(9090);
            AbstractNonblockingServer.AbstractNonblockingServerArgs args1 = new TNonblockingServer.Args(serverTransport);
            args1.processor(hello);
            TServer server = new TNonblockingServer(args1);
            System.out.println("Start server on port 9090...");
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }
}
