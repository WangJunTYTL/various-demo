package com.peaceful.io.demo.bio;

import com.peaceful.common.util.Util;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * 在单线程下，服务端没有连接时，这是client可以瞬间连接50个，但之后一会就会一个一个的才可以建立连接
 *
 * 这是服务端的当前不可以及时应答新上来的连接
 *
 * @author WangJun
 * @version 1.0 16/4/11
 */
public class SingleSocketServerDemo {

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(8888);
        Util.report("server start...");
        while (true) {
            Util.report("server wait connection...");
            Socket socket = serverSocket.accept();
            // 创建一个socket连接后，处理完后就关闭掉，会阻塞下一个socket的处理
            new SocketHandler(socket).run();
        }
    }
}
