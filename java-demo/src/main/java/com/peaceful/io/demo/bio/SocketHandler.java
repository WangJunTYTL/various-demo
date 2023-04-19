package com.peaceful.io.demo.bio;

import com.peaceful.Util;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * @author WangJun
 * @version 1.0 16/4/13
 */
public class SocketHandler implements Runnable {

    private Socket socket;

    public SocketHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        Util.report("remote address->"+socket.getRemoteSocketAddress());
        // 此时没有消息处于阻塞，有消息一条一条的处理
        InputStream inputStream = null;
        try {
            inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            line = reader.readLine();
            // 模拟处理请求时会耗费较长时间
            TimeUnit.SECONDS.sleep(1);
            while (true) {
                if (line == null || line.equals("")) {
                    OutputStream outputStream = socket.getOutputStream();
                    outputStream.write("bye bye!".getBytes());
                    outputStream.flush();
                    outputStream.close();
                    socket.close();
                    System.out.println("bye bye "+socket.getRemoteSocketAddress());
                    break;
                } else {
                    Util.report("receive msg ->" + line);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
