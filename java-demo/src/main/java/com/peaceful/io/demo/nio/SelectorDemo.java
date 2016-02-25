package com.peaceful.io.demo.nio;

import com.peaceful.common.util.Util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/26
 * @since 1.6
 */

public class SelectorDemo {

    public static void main(String[] args) throws IOException {
        // open 方法创建一个selector
        Selector selector = Selector.open();
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 80));
        socketChannel.configureBlocking(false);
        // 与Selector一起使用时，Channel必须处于非阻塞模式下。
        // 这意味着不能将FileChannel与Selector一起使用，因为FileChannel不能切换到非阻塞模式。而套接字通道都可以
        SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_READ);
        while (true) {
            Util.report("start...");
            int readyChannels = selector.select();
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
                    Util.report("is readable");
                } else if (key.isWritable()) {
                    // a channel is ready for writing
                    Util.report("is write");
                }
                keyIterator.remove();
            }
        }

    }
}
