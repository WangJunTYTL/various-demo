package com.peaceful.io.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * socket编程 转成 channel编程
 * Created by Jun on 2018/10/17.
 */
public class SocketChannelDemo {

    public static void main(String[] args) throws IOException {
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("www.baidu.com",80));
        if (channel.isConnected()){
            System.out.println("建立连接成功");
        }

        // 发送数据
        ByteBuffer sendData = ByteBuffer.allocate(100);
        sendData.put((byte) 1);
        channel.write(sendData);
        channel.socket().shutdownOutput();

        // 接收数据
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer);
        System.out.println(new String(buffer.array()));
        channel.close();
//        Socket socket = channel.socket();
//        socket.getOutputStream();
    }
}
