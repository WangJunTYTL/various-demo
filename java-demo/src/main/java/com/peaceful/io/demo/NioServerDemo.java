package com.peaceful.io.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 在Nio中，最重要的是Selector Api，Nio名字的由来是相对于之前的Socket Io，新的称为New IO
 *
 * Selector主要的作用是，我们把需要监听的事件注册到Selector，由Selector帮助我们监听网络事件，当有感兴趣的事件时通知我们。从设计模式的角度
 * 来说，是一种观察者模式
 *
 * 目前Selector监听的策略好像是轮询，轮询这种方式本身也存在性能瓶颈。
 *
 * Created by wangjun on 2016/11/10.
 */
public class NioServerDemo {

    public static void main(String[] args) throws IOException {
        // 配置服务端端口服务，在NIO中，同时面向流（Stream）的编程也转变成Channel这种方式
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.configureBlocking(false);
        channel.socket().bind(new InetSocketAddress(8888));

        // 通过open方法可以直接打开一个Selector
        Selector selector = Selector.open();
        // 注册感兴趣的事件
        channel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            selector.select(); // 等待accept event, 等待事件被处理

            System.out.println("Fount Accept Event");
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> selectionKeyIterator = keys.iterator();
            while (selectionKeyIterator.hasNext()){
                SelectionKey key = selectionKeyIterator.next();
                selectionKeyIterator.remove(); // 移除掉，这样别的线程不会在获取到通知的事件

                ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();// 获取与该key进行绑定的channel
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel.isOpen()){
                    System.out.println(socketChannel.socket().getRemoteSocketAddress());
                }
                socketChannel.write(ByteBuffer.wrap("Hello World!".getBytes()));
                socketChannel.close(); // 服务端主动关闭连接
            }

        }

    }
}
