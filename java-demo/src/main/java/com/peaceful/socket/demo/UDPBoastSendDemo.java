package com.peaceful.socket.demo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/9/14
 * @since 1.6
 */

public class UDPBoastSendDemo {

    public static void main(String[] args) throws IOException {
        MulticastSocket multicastSocket = new MulticastSocket(8080);
        InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
        DatagramSocket datagramSocket = new DatagramSocket();

        byte[] buffer = "0123456789".getBytes();
        InetAddress receiverAddress = InetAddress.getLocalHost();
        DatagramPacket packet = new DatagramPacket(
                buffer, buffer.length, receiverAddress, 8080);
        datagramSocket.send(packet);
    }

}
