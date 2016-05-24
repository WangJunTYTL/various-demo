package com.peaceful.serializable.demo;

import java.io.*;

/**
 * @author WangJun
 * @version 1.0 16/4/15
 */
public class SerializableUtil {

    private static String userDir = System.getProperty("user.dir");

    public static void serialize(Object o) throws IOException {

        OutputStream outputStream = new FileOutputStream(userDir + "/data/" + o.getClass().getSimpleName());
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        //如果对象不继承serializable就会报：java.io.NotSerializableException
        objectOutputStream.writeObject(o);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    public static Object deserialize(Class zClass) throws IOException, ClassNotFoundException {
        InputStream inputStream = new FileInputStream(userDir + "/data/"+zClass.getSimpleName());
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        return objectInputStream.readObject();
    }

    public static byte[] serializeToByte(Object o) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        //如果对象不继承serializable就会报：java.io.NotSerializableException
        objectOutputStream.writeObject(o);
        objectOutputStream.flush();
        return outputStream.toByteArray();
    }

    public static Object deserializeFromByte(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        return objectInputStream.readObject();
    }
}
