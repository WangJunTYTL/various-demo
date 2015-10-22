package com.peaceful.socket.demo;

import com.peaceful.common.util.Util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/9/14
 * @since 1.6
 */

public class SocketDemo {


    public static void main(String[] args) throws IOException {
        Client.request();
    }

    static class Client {

        public static void request() throws IOException {
            // 套接字，服务端与客户端通信
            Socket socket = new Socket("127.0.0.1", 8080);
            // 写入数据
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("hello world".getBytes());
            outputStream.flush();
            outputStream.close();
            // 关闭socket，服务端也可以主动关闭
            socket.close();
            Util.report("写入数据");
        }
    }
}
