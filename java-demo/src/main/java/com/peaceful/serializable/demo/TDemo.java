package com.peaceful.serializable.demo;

import com.alibaba.fastjson.JSON;
import com.peaceful.Util;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author WangJun
 * @version 1.0 16/4/15
 */
public class TDemo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        String str = "";
        SerializableUtil.serialize(str);
        str = (String) SerializableUtil.deserialize(str.getClass());
        Util.report(str);

        CycleQueue<String> queue = new CycleQueue<String>(8);
        queue.push("a");
        queue.push("b");
        queue.push("c");

        Util.report(JSON.toJSON(queue));

        SerializableUtil.serialize(queue);
        queue = (CycleQueue<String>) SerializableUtil.deserialize(queue.getClass());
        Util.report(queue.get());

        CycleQueue<User> userCycleQueue = new CycleQueue<User>(8);
        User user01 = new User("01",16);
        User user02 = new User("02",16);
        userCycleQueue.push(user01);
        userCycleQueue.push(user02);
        Util.report(JSON.toJSON(userCycleQueue));

        SerializableUtil.serialize(userCycleQueue);
        queue = (CycleQueue<String>) SerializableUtil.deserialize(queue.getClass());
        Util.report(queue.get());
        // 此时编码采用ISO-8859-1，其它编码不可以
        String bytes = new String(SerializableUtil.serializeToByte(queue), Charset.forName("ISO-8859-1"));
        Util.report(bytes);
        queue = (CycleQueue<String>) SerializableUtil.deserializeFromByte(bytes.getBytes(Charset.forName("ISO-8859-1")));
        Util.report(queue);
        StringBuffer buffer = new StringBuffer();
        for (byte b:bytes.getBytes(Charset.forName("ISO-8859-1"))){
            buffer.append((char)b);
        }
        Util.report(buffer.toString());
    }
}
