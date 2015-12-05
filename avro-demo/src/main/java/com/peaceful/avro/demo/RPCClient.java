package com.peaceful.avro.demo;

import com.acme.Greeting;
import com.acme.HelloWorld;
import org.apache.avro.ipc.NettyTransceiver;
import org.apache.avro.ipc.specific.SpecificRequestor;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by wangjun on 15/12/4.
 */
public class RPCClient {

    public static void main(String[] args) throws IOException {
        NettyTransceiver client = new NettyTransceiver(new InetSocketAddress(65111));
        // client code - attach to the server and send a message
        HelloWorld proxy =  SpecificRequestor.getClient(HelloWorld.class, client);
        proxy.hello(new Greeting("hello world"));
        proxy.hello(new Greeting("hello world"));
        Greeting greeting = proxy.hello(new Greeting("hello world"));
        System.out.println(greeting);
    }
}
