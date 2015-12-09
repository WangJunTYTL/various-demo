package com.peaceful.thrift.demo;

/**
 * Created by wangjun on 15/12/5.
 */

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import service.demo.Hello;

public class HelloServiceClient {
    /**
     * 调用 Hello 服务
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            // 服务传输层
            TTransport transport = new TSocket("localhost", 9090);
            transport.open();

            // 设置传输协议为 TBinaryProtocol
            // 数据序列化方式,采用binary
            TProtocol protocol = new TBinaryProtocol(transport);

            TMultiplexedProtocol mp1 = new TMultiplexedProtocol(protocol,
                    "HelloService");
            Hello.Client client = new Hello.Client(protocol,mp1);

            // 调用服务的 helloVoid 方法
            client.helloVoid();
            System.out.print(client.helloString("23"));
            System.out.print(client.helloInt(12));
            transport.close();
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }
    }
}