package com.peaceful.io.demo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by wangjun on 2016/11/11.
 */
public class FileWrite {

    public static void main(String[] args) throws IOException {
        String workDir = System.getProperty("user.dir");
        String testFile = workDir + "/src/main/java/com/peaceful/io/demo/test.0";
        File file = new File(testFile);
        if (!file.exists()) {
            file.createNewFile();
        }

        FileOutputStream outputStream = new FileOutputStream(file);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(1024);
        byteArrayOutputStream.write(" ".getBytes());
        byteArrayOutputStream.write("hello world".getBytes());

        System.out.println(outputStream.getChannel().position());
        ByteBuffer buffer = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
        outputStream.getChannel().write(buffer, outputStream.getChannel().position()+1024-buffer.remaining());
        outputStream.flush();
        outputStream.close();


    }
}
