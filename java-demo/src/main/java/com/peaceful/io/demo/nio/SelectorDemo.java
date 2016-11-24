package com.peaceful.io.demo.nio;

import com.peaceful.common.util.Util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/26
 * @since 1.6
 */

public class SelectorDemo {

    public static void main(String[] args) throws IOException, InterruptedException {
        // open 方法创建一个selector,selector相当于是一个观察者 或者是一个网络事件监听者
        Selector selector = Selector.open();
        // 新创建的selector注册的channel为0
        System.out.println("所有注册的channel：" + selector.keys().size());
        System.out.println("io准备完毕的channel：" + selector.selectedKeys().size());

        // 连接到远程服务器
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 80));
        socketChannel.configureBlocking(false);

        SocketChannel socketChannel02 = SocketChannel.open();
        socketChannel02.connect(new InetSocketAddress("127.0.0.1", 8080));
        socketChannel02.configureBlocking(false);


        // 与Selector一起使用时，Channel必须处于非阻塞模式下。
        // 这意味着不能将FileChannel与Selector一起使用，因为FileChannel不能切换到非阻塞模式。而套接字通道都可以
        socketChannel.register(selector, SelectionKey.OP_READ);
        socketChannel02.register(selector, SelectionKey.OP_READ);

        System.out.println("所有注册的channel：" + selector.keys().size());

        int time = 0;
        while (true && time < 6) {
            time++;
            Util.report("time..." + time);
            // 将会阻塞
            int readyChannels = selector.select();
            System.out.println(time+"->io准备完毕的channel：" + selector.selectedKeys().size());
            System.out.println(time+"->所有注册的channel：" + selector.keys().size());

            if (readyChannels == 0) continue;
            Set selectedKeys = selector.selectedKeys();
            Iterator keyIterator = selectedKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = (SelectionKey) keyIterator.next();
                if (key.isAcceptable()) {
                    // a connection was accepted by a ServerSocketChannel.
                    Util.report("is acceptable");
                } else if (key.isConnectable()) {
                    // a connection was established with a remote server.
                    Util.report("is connectable");
                } else if (key.isReadable()) {
                    // a channel is ready for reading
                    Util.report(key.channel()+"is readable");
                    // 这里是向web服务器发送连接，如果web服务器响应数据，则可能是连接超时，则主动断开socket
                    key.channel().close();
                } else if (key.isWritable()) {
                    // a channel is ready for writing
                    Util.report(key.channel()+"is write");
                }
                keyIterator.remove();
            }
        }

    }
}
