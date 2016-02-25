package com.peaceful.io.demo.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 文件映射到内存，从内存中读取文件
 * <p/>
 * Created by wangjun on 16/2/22.
 */
public class MappedByteBufferDemo {

    public static void main(String[] args) throws IOException {
        String userDir = System.getProperty("user.dir");
        RandomAccessFile accessFile = new RandomAccessFile(userDir + "/data/nio-data.txt", "rw");
        MappedByteBuffer byteBuffer = accessFile.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, accessFile.length());
        while (byteBuffer.hasRemaining()) {
            System.out.print((char) byteBuffer.get());
        }
        accessFile.close();

    }
}
