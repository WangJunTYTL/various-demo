package com.peaceful.socket.demo;

import com.peaceful.Util;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/9/14
 * @since 1.6
 */

public class ServerSocketDemo {
    static ServerSocket serverSocket;

    public static void main(String[] args) throws IOException {
        serverSocket = new ServerSocket(8080);

        Server.echo();
    }


    static class Server {

        public static void echo() throws IOException {
            for (; ; ) {
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                //需要注意的是，从Socket的输入流中读取数据并不能读取文件那样，一直调用read() 方法直到返回 - 1 为止，
                // 因为对Socket而言，只有当服务端关闭连接时，Socket的输入流才会返回-1，而是事实上服务器并不会不停地关闭连接。
                // 假设我们想要通过一个连接发送多个请求，那么在这种情况下关闭连接就显得非常愚蠢。
//                Util.report("bytes length " + inputStream.available()); //这地方调用是有上述所说的问题
                byte[] bytes = new byte[1024];
                inputStream.read(bytes);
                System.out.println(new String(bytes));
                inputStream.close();
//                socket.close();
                Util.report("echo data ");

            }
        }

    }


}
