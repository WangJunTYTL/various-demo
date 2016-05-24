package com.peaceful.io.demo.bio;

import com.peaceful.common.util.Util;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 长连接
 *
 *
 * @author WangJun
 * @version 1.0 16/4/13
 */
public class LiveSocketServerDemo {

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(8888);
        Util.report("server start...");
        while (true) {
            Util.report("server wait connection...");
            Socket socket = serverSocket.accept();
            new SocketHandler(socket).run();
        }
    }
}
