package com.peaceful.demo.protostuff;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

/**
 * Created by wangjun38 on 2018/6/13.
 */
public class T1 {

    public static void main(String[] args) throws IOException {
        User user = new User();
        user.setId(1);
        user.setName("李白");

        // 序列化
        Schema schema = RuntimeSchema.getSchema(user.getClass());
        // ProtobufIOUtil是暴露的api
        byte[] bytes = ProtobufIOUtil.toByteArray(user,schema, LinkedBuffer.allocate(256));
        // 序列化成字节数组后，字节的大小是不是比Java自带的序列化方式要简单
        System.out.println(Arrays.toString(bytes));

        // 反序列化
        User userN= new User();
        ProtobufIOUtil.mergeFrom(bytes,userN,schema);
        System.out.println(userN);


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectInputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectInputStream.writeObject(user);
        byte[] bytes1 = byteArrayOutputStream.toByteArray();
        System.out.println(Arrays.toString(bytes1));
    }
}
