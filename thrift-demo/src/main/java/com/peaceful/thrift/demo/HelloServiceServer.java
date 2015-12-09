package com.peaceful.thrift.demo;

/**
 * Created by wangjun on 15/12/5.
 */

import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.TProcessor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import service.demo.Hello;
import service.demo.World;

public class HelloServiceServer {
    /**
     * 启动 Thrift 服务器
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            // hello 协议的处理类
            TProcessor hello = new Hello.Processor(new HelloServiceImpl());
            TProcessor world  = new World.Processor(new WorldServiceImpl());

            TMultiplexedProcessor processor = new TMultiplexedProcessor();
            processor.registerProcessor("HelloService", hello);
            processor.registerProcessor("WorldService", world);

            // 服务传输层
            TServerTransport serverTransport = new TServerSocket(9090);
            TServer server = new TSimpleServer(new TThreadPoolServer.Args(serverTransport).processor(processor));
            System.out.println("Start server on port 9090...");
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }
}
