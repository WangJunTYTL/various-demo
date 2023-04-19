package com.peaceful.io.demo.nio;

import com.peaceful.Util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * @author WangJun
 * @version 1.0 16/4/13
 */
public class SocketServerDemo {


    public static void main(String[] args) {
        Selector selector = null;
        ServerSocketChannel serverSocketChannel = null;

        try {
            // Selector for incoming time requests
            selector = Selector.open();

            // Create a new server socket and set to non blocking mode
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);

            // Bind the server socket to the local host and port
            serverSocketChannel.socket().setReuseAddress(true);
            serverSocketChannel.socket().bind(new InetSocketAddress(8888));

            // Register accepts on the server socket with the selector. This
            // step tells the selector that the socket wants to be put on the
            // ready list when accept operations occur, so allowing multiplexed
            // non-blocking I/O to take place.
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            // Here's where everything happens. The select method will
            // return when any operations registered above have occurred, the
            // thread has been interrupted, etc.
            while (selector.select() > 0) {
                // Someone is ready for I/O, get the ready keys
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();

                // Walk through the ready keys collection and process date requests.
                while (it.hasNext()) {
                    SelectionKey readyKey = it.next();
                    it.remove();

                    // The key indexes into the selector so you
                    // can retrieve the socket that's ready for I/O
                    execute((ServerSocketChannel) readyKey.channel());
                }
            }
        } catch (ClosedChannelException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                selector.close();
            } catch (Exception ex) {
            }
            try {
                serverSocketChannel.close();
            } catch (Exception ex) {
            }
        }
    }

    private static void execute(ServerSocketChannel serverSocketChannel) throws IOException {
        SocketChannel socketChannel = null;

        try {
            socketChannel = serverSocketChannel.accept();
            Util.report("client->"+socketChannel.socket().getRemoteSocketAddress());
            MyRequestObject myRequestObject = receiveData(socketChannel);
            Util.report(myRequestObject.toString());
            TimeUnit.SECONDS.sleep(1);
            MyResponseObject myResponseObject = new MyResponseObject(
                    "response for " + myRequestObject.getName(),
                    "response for " + myRequestObject.getValue());
            sendData(socketChannel, myResponseObject);
            Util.report(myResponseObject.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                socketChannel.close();
            } catch (Exception ex) {
            }
        }
    }

    private static MyRequestObject receiveData(SocketChannel socketChannel) throws IOException {
        MyRequestObject myRequestObject = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        try {
            byte[] bytes;
            int size = 0;
            while ((size = socketChannel.read(buffer)) >= 0) {
                buffer.flip();
                bytes = new byte[size];
                buffer.get(bytes);
                baos.write(bytes);
                buffer.clear();
            }
            bytes = baos.toByteArray();
            Object obj = SerializableUtil.toObject(bytes);
            myRequestObject = (MyRequestObject) obj;
        } finally {
            try {
                baos.close();
            } catch (Exception ex) {
            }
        }
        return myRequestObject;
    }

    private static void sendData(SocketChannel socketChannel, MyResponseObject myResponseObject) throws IOException {
        byte[] bytes = SerializableUtil.toBytes(myResponseObject);
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        socketChannel.write(buffer);
    }
}
