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


public class HelloServiceServer1_1 {
    /**
     * 启动 Thrift 服务器
     */
    public static void main(String[] args) {
        ensureServerLive();
    }

    public static void ensureServerLive() {
        while (true) {
            TServer server = null;
            try {
                TProcessor hello = new HelloServer.Processor(new HelloServiceImpl());

                TServerTransport serverTransport = new TServerSocket(9090);
                TThreadPoolServer.Args serverArgs = new TThreadPoolServer.Args(serverTransport).processor(hello);
                serverArgs.maxWorkerThreads(5);
                server = new TThreadPoolServer(serverArgs);
                System.out.println("Start server on port 9090...");
                // 生产环境需要保证server存活
                server.serve();
            } catch (Exception e) {
                e.printStackTrace();
                server.stop();
                System.out.println("将重启server");
            }
        }
    }
}
