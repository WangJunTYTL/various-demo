package com.peaceful.io.demo.bio;

import com.peaceful.common.util.Util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author WangJun
 * @version 1.0 16/4/11
 */
public class LiveSocketClientDemo {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 8888);
        // TCP协议层的心跳，防止长时间不传输数据被系统防火墙检测到被关闭
        socket.setKeepAlive(true);
        new SocketTick(socket).start();
        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String next = scanner.nextLine();
            outputStream.write(next.getBytes());
            // 记得每次发送完一行数据后，需要发送换行符
            outputStream.write("\n".getBytes());
            outputStream.flush();
            Util.report("send msg->" + next);
        }
    }

    static class SocketTick extends Thread {

        private Socket socket;

        public SocketTick(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            while (true) {
                // 其实在服务端关闭连接后，客户端并不知道是否关闭，只有尝试发送数据观察是否关闭,下面是服务端关闭连接后的状态，会一直保持下面的状态
                // tcp4       0      0  127.0.0.1.8888         127.0.0.1.63594        FIN_WAIT_2
                // tcp4       8      0  127.0.0.1.63594        127.0.0.1.8888         CLOSE_WAIT
                // 可以看出客户端一直停留在CLOSE_WAIT状态
                // 服务端一直停留在FIN_WAIT_2状态
                if (socket.isClosed()) {
                    Util.report("socked is closed");
                } else {
                    try {
                        socket.sendUrgentData(0xFF);
                    } catch (IOException e) {
                        try {
                            socket.close();
                            System.exit(1);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        e.printStackTrace();
                    }
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
