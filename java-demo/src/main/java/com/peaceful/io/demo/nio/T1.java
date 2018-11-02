package com.peaceful.io.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by Jun on 2018/10/17.
 */
public class T1 {

    public static void main(String[] args) throws IOException {
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("www.baidu.com",80));
        channel.configureBlocking(false);

        Selector selector = Selector.open();
        SelectionKey selectionKey = channel.register(selector, SelectionKey.OP_CONNECT);

        System.out.println(selector.selectedKeys());
        System.out.println(selectionKey);

        while (selector.select() > 0) {
            //5. 获取监听器上所有的监听事件值
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();

            //6. 如果有值
            while (it.hasNext()) {
                //7. 取到SelectionKey
                SelectionKey key = it.next();

                System.out.println("aaa");
                //11. 移除当前key
                it.remove();
            }
        }



    }
}
