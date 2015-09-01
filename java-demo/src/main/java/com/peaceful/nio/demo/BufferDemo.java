package com.peaceful.nio.demo;

import com.peaceful.common.util.Util;

import java.nio.ByteBuffer;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/26
 * @since 1.6
 */

public class BufferDemo {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(6);
        byte b = 12;
        byteBuffer.put(b);
        Util.report("字节b:"+b);
        Util.report("字节b:"+byteBuffer.getInt());
    }
}
