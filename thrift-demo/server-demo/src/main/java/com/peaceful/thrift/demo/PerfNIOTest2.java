package com.peaceful.thrift.demo;

/**
 * Created by Jun on 15/12/5.
 */

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.perf4j.StopWatch;
import org.perf4j.log4j.Log4JStopWatch;

import service.demo.HelloServer;


public class PerfNIOTest2 {
    /**
     * 测试Thrift的性能
     */
    public static void main(String[] args) throws TTransportException {
        TTransport transport = new TFramedTransport(new TSocket("localhost", 9090));  //非阻塞
        transport.open();
        try {
            TProtocol protocol = new TBinaryProtocol(transport);
            HelloServer.Client client = new HelloServer.Client(protocol);
            StopWatch watch = new Log4JStopWatch("Thrift");
            for (int i=0;i<Long.MAX_VALUE;i++) {
                watch.start();
                client.helloString("23");
                watch.stop();
            }
        }catch (Exception e){
            e.printStackTrace();

        }
    }
}