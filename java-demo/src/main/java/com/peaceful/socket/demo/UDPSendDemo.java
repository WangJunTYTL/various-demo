package com.peaceful.socket.demo;

import java.io.IOException;
import java.net.*;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/9/14
 * @since 1.6
 */

public class UDPSendDemo {

    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket();

        byte[] buffer = "0123456789".getBytes();
        InetAddress receiverAddress = InetAddress.getLocalHost();
        DatagramPacket packet = new DatagramPacket(
                buffer, buffer.length, receiverAddress, 8080);
        datagramSocket.send(packet);
    }

}
