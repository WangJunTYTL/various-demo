package com.peaceful.io.demo.nio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jun on 2018/12/1.
 */
public class NIODemo {

    public static void main(String[] args) throws IOException, InterruptedException {
        Selector selector = Selector.open();
        SelectorThread selectorThread = new SelectorThread(selector);
        new Thread(selectorThread).start();


        Server server = new Server();
        new Thread(server).start();

        TimeUnit.SECONDS.sleep(1);

        Client client = new Client(selector);
        new Thread(client).start();
    }

    public static class Server implements Runnable {
        ServerSocket serverSocket;


        @Override
        public void run() {
            System.out.println("server running");
            try {
                serverSocket = new ServerSocket(8080);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                System.out.println("server get request：" + socket.getInetAddress().toString());
                for (;;) {
                    byte[] bytes = new byte[1024];
                    int totalSize = socket.getInputStream().read(bytes);
                    ByteBuffer buffer = ByteBuffer.allocate(totalSize);
                    buffer.put(bytes, 0, totalSize);
                    System.out.println("server read msg:" + new String(buffer.array()));
                    TimeUnit.SECONDS.sleep(6);
                    OutputStream outputStream = socket.getOutputStream();
                    outputStream.write("Hi".getBytes());
                    outputStream.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static class Client implements Runnable {

        private Selector selector;

        public Client(Selector selector) {
            this.selector = selector;
        }

        @Override
        public void run() {
            SocketChannel socketChannel = null;
            try {
                socketChannel = SocketChannel.open();
                socketChannel.connect(new InetSocketAddress("localhost", 8080));
                socketChannel.configureBlocking(false);
                System.out.println("client is connect:" + socketChannel.finishConnect());
//                selector.wakeup();
                socketChannel.register(selector, SelectionKey.OP_WRITE);
                System.out.println("client register event:write");
            } catch (IOException e) {
                e.printStackTrace();
            }
//            selector.wakeup();

        }
    }


    public static class SelectorThread implements Runnable {

        private Selector selector;

        public SelectorThread(Selector selector) {
            this.selector = selector;
        }

        @Override
        public void run() {
            System.out.println("selector running");
            for (; ; ) {
                if (selector == null) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("selector is null");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
//                        int keySize = selector.select(1000);
                        int keySize = selector.select(1000);
                        Iterator<SelectionKey> selectionKeys = selector.selectedKeys().iterator(); // 获取所有事件(key)
                        while (selectionKeys.hasNext()) {
                            SelectionKey key = selectionKeys.next();
                            selectionKeys.remove();
                            Object o = key.attachment();
                            System.out.println("selector key attachment: " + o + "k" + key.readyOps());
                            if (!key.isValid()) {
                                key.cancel();
                                System.out.println("cancel");
                            }
                            if (key.isWritable()) { // 判断是否可写
                                SocketChannel channel = (SocketChannel) key.channel();
                                channel.write(ByteBuffer.wrap("hello world".getBytes()));
                                key.interestOps(SelectionKey.OP_READ); // 希望可读时通知(key)
                            } else if (key.isReadable()) { // 判断是否可读
                                SocketChannel channel = (SocketChannel) key.channel();
                                ByteBuffer buffer = ByteBuffer.allocate(1024);
                                channel.read(buffer);
                                byte[] data = new byte[buffer.position()];
                                for (int i=0;i<data.length;i++){
                                    data[i] = buffer.get(i);
                                }
                                System.out.println(new String(data));
//                                channel.register(selector,SelectionKey.OP_WRITE);
                                key.interestOps(SelectionKey.OP_WRITE); // 希望可写时，发送通知（key）
                            }
                        }
                        if (keySize > 0)
                            System.out.println("select ksySize:" + keySize);
                    } catch (IOException e) {
                        try {
                            selector.close();
                            e.printStackTrace();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
