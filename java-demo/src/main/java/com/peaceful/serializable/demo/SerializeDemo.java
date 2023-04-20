package com.peaceful.serializable.demo;

import com.alibaba.fastjson.JSON;
import com.peaceful.Util;

import java.io.*;

/**
 * @author WangJun
 * @version 1.0 16/4/15
 */
public class SerializeDemo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // 自定义对象序列化
        User user = new User("wj", 28);
        user.sex = 1;
        //先通过json的方式
        Util.report(JSON.toJSON(user));


        // 通过Java提供的字节序列化方式
        String userDir = System.getProperty("user.dir");
        OutputStream outputStream = new FileOutputStream(userDir + "/data/serializable.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        //如果user对象不继承serializable就会报：java.io.NotSerializableException
        objectOutputStream.writeObject(user);
        objectOutputStream.flush();
        objectOutputStream.close();

        InputStream inputStream = new FileInputStream(userDir+"/data/serializable.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        user = (User) objectInputStream.readObject();
        Util.report(user);


    }
}
