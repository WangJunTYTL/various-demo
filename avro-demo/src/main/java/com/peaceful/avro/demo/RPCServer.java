package com.peaceful.avro.demo;

import com.acme.Curse;
import com.acme.Greeting;
import com.acme.HelloWorld;
import org.apache.avro.AvroRemoteException;
import org.apache.avro.ipc.NettyServer;
import org.apache.avro.ipc.Server;
import org.apache.avro.ipc.specific.SpecificResponder;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangjun on 15/12/4.
 */
public class RPCServer {

    private static Server server;

    private static class HelloWorldImpl implements HelloWorld {

        @Override
        public Greeting hello(Greeting greeting) throws AvroRemoteException, Curse {
            System.out.println("Sending message:" + greeting.getMessage());
            return greeting;
        }
    }

    private static ScheduledExecutorService scheduledExecutorService =  Executors.newSingleThreadScheduledExecutor();;


    public static void main(String[] args) {
        server = new NettyServer(new SpecificResponder(HelloWorld.class, new HelloWorldImpl()), new InetSocketAddress(65111));
        server.start();
        final NettyServer srv = (NettyServer)server;
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(srv.getNumActiveConnections());
            }
        },0,5, TimeUnit.SECONDS);
    }
}
