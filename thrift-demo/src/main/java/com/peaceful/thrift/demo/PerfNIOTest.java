package com.peaceful.thrift.demo;

/**
 * Created by wangjun on 15/12/5.
 */

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.perf4j.StopWatch;
import org.perf4j.log4j.Log4JStopWatch;

import java.io.IOException;

import service.demo.HelloServer;


public class PerfNIOTest {
    /**
     * 测试Thrift的性能
     */
    public static void main(String[] args) {
        try {
            // I/O层  通过Socket方式连接Server
            TTransport transport = new TNonblockingSocket("localhost", 9090);
            // 设置数据序列化与反序列化方式 TBinaryProtocol
            // 数据序列化方式,采用binary，用哪种协议，取决于thrift工具生成的代码，在生成的代码里面已经写好了序列化和反序列化的方式
            TProtocol protocol = new TBinaryProtocol(transport);


            //TMultiplexedProtocol mp1 = new TMultiplexedProtocol(protocol, "HelloService"); // 指定远程服务名
            HelloServer.Client client = new HelloServer.Client(protocol);
            StopWatch watch = new Log4JStopWatch("Thrift");
            for (int i=0;i<Long.MAX_VALUE;i++) {
                watch.start();
                client.helloString("23");
//                client.helloInt(1);
//                System.out.println();
                watch.stop();
            }
            transport.close();
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}