package com.peaceful.io.demo.nio;

import com.peaceful.common.util.Util;

import java.nio.ByteBuffer;

/**
 * 了解position limit capacity 的意义
 *
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/26
 * @since 1.6
 */

public class BufferDemo {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(6);
        byteBuffer.put((byte)1);
        byteBuffer.put((byte)1);
        byteBuffer.put((byte)1);
        byteBuffer.put((byte)1);
        byteBuffer.put((byte)1);
        byteBuffer.put((byte)1);
        Util.report("position :" + byteBuffer.position() + "\tlimit: " + byteBuffer.limit() + "\t capacity: " + byteBuffer.capacity());
        byteBuffer.flip();
        Util.report("position :" + byteBuffer.position() + "\tlimit: " + byteBuffer.limit() + "\t capacity: " + byteBuffer.capacity());
        Util.report("字节b:" + byteBuffer.get());
        Util.report("position :" + byteBuffer.position() + "\tlimit: " + byteBuffer.limit() + "\t capacity: " + byteBuffer.capacity());


    }
}
