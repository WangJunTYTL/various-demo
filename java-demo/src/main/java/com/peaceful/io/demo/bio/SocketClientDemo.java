package com.peaceful.io.demo.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author WangJun
 * @version 1.0 16/4/11
 */
public class SocketClientDemo {

    public static void main(String[] args) throws IOException {
        for (int i=0;i<15000;i++) {
            Socket socket = new Socket("localhost", 8888);
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("Hello world!".getBytes());
            outputStream.flush();
            outputStream.close();
            socket.close();
            System.out.println("send id->"+i);
        }
    }
}
