package com.peaceful.socket.demo;

import com.peaceful.common.util.Util;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/9/14
 * @since 1.6
 */

public class UDPReceiveDemo {

    public static void main(String[] args) throws IOException {

        DatagramSocket datagramSocket = new DatagramSocket(8080);
        for (; ; ) {
            byte[] buffer = new byte[10];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            datagramSocket.receive(packet);
            Util.report(new String(buffer));
        }
    }
}
