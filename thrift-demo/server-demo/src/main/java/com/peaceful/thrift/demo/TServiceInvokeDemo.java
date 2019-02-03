package com.peaceful.thrift.demo;

import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TMemoryBuffer;
import org.apache.thrift.transport.TTransport;

import java.io.UnsupportedEncodingException;

import service.demo.HelloServer;

/**
 * 模拟Thrift整个调用过程
 *
 * Created by Jun on 2018/8/28.
 */
public class TServiceInvokeDemo {

    public static void main(String[] args) throws TException, UnsupportedEncodingException {
        TTransport tTransport = new TMemoryBuffer(1024);
        TProtocol jsonProtocol = new TJSONProtocol.Factory().getProtocol(tTransport);

       /* // 模拟数据序列化过程
        FirstRequest request = new FirstRequest();
        request.setNumber(1);
        request.setMsg("hello");
        request.write(jsonProtocol);

        // 模拟对象序列化过程
        System.out.println(((TMemoryBuffer) tTransport).toString("UTF-8"));
        request.read(jsonProtocol);
        tTransport.flush();*/


        TProcessor tProcessor = new HelloServer.Processor(new HelloServiceImpl());
//        tProcessor.process(jsonProtocol,jsonProtocol);

        // 服务端收到请求后调用方法
        TTransport outTransport = new TMemoryBuffer(1024);
        TProtocol outJsonProtocol = new TJSONProtocol.Factory().getProtocol(outTransport);

        HelloServer.Client client = new HelloServer.Client.Factory().getClient(jsonProtocol);

        // 模拟客户端发送方法调用
        client.send_helloInt(12);
        System.out.println("request->"+((TMemoryBuffer) tTransport).toString("UTF-8"));

        // 调用过程
        tProcessor.process(jsonProtocol,outJsonProtocol);

        System.out.println("response->"+((TMemoryBuffer) outTransport).toString("UTF-8"));





    }
}
