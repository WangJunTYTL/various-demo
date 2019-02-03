package com.peaceful.thrift.demo;

/**
 * Created by Jun on 15/12/5.
 */

import org.apache.thrift.TProcessor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

import service.demo.HelloServer;


public class HelloServiceServer1 {
    /**
     * 启动 Thrift 服务器
     */
    public static void main(String[] args) {
        try {
            // hello 服务实现类
            TProcessor hello = new HelloServer.Processor(new HelloServiceImpl());

            //0.9版本支持一个端口下面可以提供多个服务,原理是在调用某个服务时在发送协议数据中写入服务的名称，服务端先解析协议数据去搜索对应服务
            //正式使用 建议采用最新版本
            //TMultiplexedProcessor processor = new TMultiplexedProcessor();
            //processor.registerProcessor("HelloService", hello);
            //processor.registerProcessor("WorldService", world);

            // 服务传输层,通过Socket实现通讯服务，socket是阻塞的
            TServerTransport serverTransport = new TServerSocket(9090);
            TServer.AbstractServerArgs serverArgs = new TThreadPoolServer.Args(serverTransport).processor(hello);
            TServer server = new TSimpleServer(serverArgs);
            System.out.println("Start server on port 9090...");
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }
}
