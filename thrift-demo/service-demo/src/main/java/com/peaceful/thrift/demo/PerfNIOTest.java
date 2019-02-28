package com.peaceful.thrift.demo;

/**
 * Created by Jun on 15/12/5.
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
            TTransport transport = new TSocket("localhost", 9090);
            transport.open();

            TProtocol protocol = new TBinaryProtocol(transport);

            //TMultiplexedProtocol mp1 = new TMultiplexedProtocol(protocol, "HelloService"); // 指定远程服务名
            HelloServer.Iface client = new HelloServer.Client(protocol);
            StopWatch watch = new Log4JStopWatch("Thrift");
            for (int i=0;i<Long.MAX_VALUE;i++) {
                watch.start();
                client.helloString("23");
                watch.stop();
            }
            transport.close();
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }
    }
}