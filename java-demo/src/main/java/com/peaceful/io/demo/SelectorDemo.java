package com.peaceful.io.demo;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * Created by wangjun38 on 2018-12-10.
 */
public class SelectorDemo {

    public static void main(String[] args) throws IOException {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        Selector selector = Selector.open();

        channel.register(selector, SelectionKey.OP_WRITE);
        System.out.println(selector.keys().size());
        SelectionKey key = channel.register(selector, SelectionKey.OP_WRITE);
        System.out.println(selector.keys().size());
        key.cancel();
        System.out.println(selector.keys().size());
    }
}
