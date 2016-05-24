package com.peaceful.io.demo.bio;

import com.peaceful.common.util.Util;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 在多线程下，上来可以接受很多连接，但在测试时发现最多收到8000多个，客户端就会超时了，但在单线程霞不会报超时的异常，会一个接一个被处理
 *
 * @author WangJun
 * @version 1.0 16/4/11
 */
public class MultiThreadSocketServerDemo {

    private static final Executor executor = Executors.newFixedThreadPool(8);

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(8888);
        Util.report("server start...");
        while (true) {
            Util.report("server wait connection...");
            Socket socket = serverSocket.accept();
            executor.execute(new SocketHandler(socket));
        }
    }

}
