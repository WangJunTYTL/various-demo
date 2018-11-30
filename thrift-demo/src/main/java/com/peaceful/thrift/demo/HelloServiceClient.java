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


public class HelloServiceClient {
    /**
     * 调用 Hello 服务
     */
    public static void main(String[] args) {
        try {
            // I/O层  通过Socket方式连接Server
            TTransport transport = new TSocket("localhost", 9090);
            transport.open();

            // 设置数据序列化与反序列化方式 TBinaryProtocol
            // 数据序列化方式,采用binary，用哪种协议，取决于thrift工具生成的代码，在生成的代码里面已经写好了序列化和反序列化的方式
//            TProtocol protocol = new TBinaryProtocol(transport);

            // 定义自己的传输协议
            TCustomerProtocol protocol = new TCustomerProtocol(transport);


            //TMultiplexedProtocol mp1 = new TMultiplexedProtocol(protocol, "HelloService"); // 指定远程服务名
            HelloServer.Client client = new HelloServer.Client(protocol);

            // 调用服务的 helloVoid 方法
            client.helloVoid();
            System.out.print(client.helloString("23"));
            System.out.print(client.helloInt(12));
            client.helloNull();
            transport.close();
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }
    }
}