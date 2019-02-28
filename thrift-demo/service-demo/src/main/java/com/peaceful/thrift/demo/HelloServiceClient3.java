package com.peaceful.thrift.demo;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

import service.demo.HelloServer;

/**
 * Created by Jun on 2018/11/29.
 */
public class HelloServiceClient3 {

    private static Logger logger = LoggerFactory.getLogger(HelloServiceClient3.class);

    public static void main(String[] args) throws TException{
        // TFramedTransport 在发送数据时，会先计算字节长度，用4个字节表示数据长度追加到数据的最前面，然后在发送
        TTransport transport = new TFramedTransport(new TSocket("localhost", 9090));  //非阻塞
        transport.open();
        try {
            TProtocol protocol = new TBinaryProtocol(transport);
            HelloServer.Iface client = new HelloServer.Client(protocol);
            System.out.println(client.helloString("123"));
            System.out.println(client.helloString("123"));
            System.out.println(client.helloInt(1));
        }catch (Exception e){
            logger.error("",e);

        }


    }
}
