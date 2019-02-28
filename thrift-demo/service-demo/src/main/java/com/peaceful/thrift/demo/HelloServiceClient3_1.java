package com.peaceful.thrift.demo;

/**
 * Created by Jun on 15/12/5.
 */

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TTransportException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import service.demo.HelloServer;


public class HelloServiceClient3_1 {
    /**
     * 调用 Hello 服务
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        try {
            TAsyncClientManager manager = new TAsyncClientManager();
            TNonblockingSocket nonblockingSocket = new TNonblockingSocket("localhost", 9090);

            // 异步调用
            HelloServer.AsyncClient client = new HelloServer.AsyncClient(new TBinaryProtocol.Factory(), manager, nonblockingSocket);
            AsyncMethodCallback callback = new AsyncMethodCallback<HelloServer.AsyncClient.helloString_call>() {
                @Override
                public void onComplete(HelloServer.AsyncClient.helloString_call response) {
                    try {
                        System.out.println(response.getResult());
                    } catch (TException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(Exception exception) {
                    exception.printStackTrace();
                }
            };
            client.helloString("123", callback);
            // 每次调用，都需要重新创建client实例,下面会报错
            // client.helloInt(1,callback );
            HelloServer.AsyncClient.Factory factory = new HelloServer.AsyncClient.Factory(manager, new TBinaryProtocol.Factory());

            // 测试可以建立的最大连接数
//            int i = 0;
//            for (;;){
//                i++;
//                try {
//                    factory.getAsyncClient(new TNonblockingSocket("localhost", 9090)).helloString("223", callback);
//                }catch (Exception e){
//                    System.out.println(i);
//                    System.exit(0);
//                }
//            }
            TimeUnit.SECONDS.sleep(5);
            // 下面只会执行最后一个方法
            factory.getAsyncClient(nonblockingSocket).helloString("323",callback);
            factory.getAsyncClient(nonblockingSocket).helloString("423",callback);
            factory.getAsyncClient(nonblockingSocket).helloString("523",callback);
            factory.getAsyncClient(nonblockingSocket).helloString("523",callback);

            // 控制socket的关闭
            TNonblockingSocket v1 = new TNonblockingSocket("localhost", 9090);
            factory.getAsyncClient(v1).helloString("623", new AsyncMethodCallback<HelloServer.AsyncClient.helloString_call>() {
                @Override
                public void onComplete(HelloServer.AsyncClient.helloString_call response) {
                    try {
                        System.out.println(response.getResult());
                    } catch (TException e) {
                        e.printStackTrace();
                    }
                    v1.close();
                }

                @Override
                public void onError(Exception exception) {
                    v1.close();

                }
            });
            TNonblockingSocket v2 = new TNonblockingSocket("localhost", 9090);
            factory.getAsyncClient(v2).helloString("723", new AsyncMethodCallback<HelloServer.AsyncClient.helloString_call>() {
                @Override
                public void onComplete(HelloServer.AsyncClient.helloString_call response) {
                    try {
                        System.out.println(response.getResult());
                    } catch (TException e) {
                        e.printStackTrace();
                    }
                    v2.close();
                }

                @Override
                public void onError(Exception exception) {
                    v2.close();
                }
            });
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }

        TimeUnit.SECONDS.sleep(6);
    }
}