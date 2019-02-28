package com.peaceful.io.demo;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Jun on 2018-12-08.
 */
public class HttpSocketDemo {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", 9090));
        Selector selector = Selector.open();

        new SelectorThread(selector).start();

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            selector.wakeup();
            socketChannel.register(selector, SelectionKey.OP_READ);
            System.out.println("new connection...");
            selector.wakeup();
        }

    }

    static class SelectorThread extends Thread {

        private Selector selector;

        public SelectorThread(Selector selector) {
            this.selector = selector;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println("selector running");
                    selector.select();
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isValid() && key.isReadable()) {
                            SocketChannel socketChannel = (SocketChannel) key.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            socketChannel.read(buffer);
                            System.out.println(new String(readBuffer(buffer), "UTF-8"));
                            key.interestOps(SelectionKey.OP_WRITE);
                        } else if (key.isValid() && key.isWritable()) {
                            SocketChannel socketChannel = (SocketChannel) key.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            String response ="HTTP/1.1 200 OK\n" +
                                    "Date: Sat, 08 Dec 2018 12:30:26 GMT\n" +
                                    "Server: Apache\n" +
                                    "Last-Modified: Tue, 12 Jan 2010 13:48:00 GMT\n" +
                                    "ETag: \"51-47cf7e6ee8400\"\n" +
                                    "Accept-Ranges: bytes\n" +
                                    "Content-Length: 81\n" +
                                    "Cache-Control: max-age=86400\n" +
                                    "Expires: Sun, 09 Dec 2018 12:30:26 GMT\n" +
                                    "Connection: Keep-Alive\n" +
                                    "Content-Type: text/html\n" +
                                    "\n" +
                                    "<html>\n" +
                                    "<meta http-equiv=\"refresh\" content=\"0;url=http://www.baidu.com/\">\n" +
                                    "</html>";
                            buffer.put(response.getBytes(Charset.forName("UTF-8")));
                            socketChannel.write(buffer);
                            System.out.println("response ...");
                            socketChannel.socket().close();
                        } else if (!key.isValid()) {
                            System.out.println("key is cannel");
                            key.cancel();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public byte[] readBuffer(ByteBuffer buffer) {
            int postion = buffer.position();
            byte[] bytes = new byte[postion];
            for (int i = 0; i < postion; i++) {
                bytes[i] = buffer.array()[i];
            }
            return bytes;
        }
    }
}
