package com.peaceful.serializable.demo;

import com.alibaba.fastjson.JSON;

import java.io.*;

/**
 * Created by JunWang on 2017/1/4.
 */
public class T1 {

    public static void main(String[] args) throws IOException {

        CustomerSeria customerSeria = new CustomerSeria();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);

        ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
        outputStream.writeObject(customerSeria);
        System.out.println(byteArrayOutputStream.toString());
    }


    private static class CustomerSeria implements Serializable {

        public int a = 1;
        private void writeObject(java.io.ObjectOutputStream s) throws IOException {
            s.writeInt(2);
        }
    }
}
