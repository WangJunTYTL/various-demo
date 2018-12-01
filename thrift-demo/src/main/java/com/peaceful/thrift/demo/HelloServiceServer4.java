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


public class HelloServiceServer4 {
    /**
     * 启动 Thrift 服务器
     */
    public static void main(String[] args) {
        try {
            TProcessor hello = new HelloServer.Processor(new HelloServiceImpl());

            TServerTransport serverTransport = new TServerSocket(9090);
            TServer.AbstractServerArgs serverArgs = new TThreadPoolServer.Args(serverTransport).processor(hello);
            // 设置协议处理类，默认是TBinaryProtocol
            serverArgs.inputProtocolFactory(new TCustomerProtocol.Factory());
            serverArgs.outputProtocolFactory(new TCustomerProtocol.Factory());
            TServer server = new TSimpleServer(serverArgs);
            System.out.println("Start server on port 9090...");
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }
}
