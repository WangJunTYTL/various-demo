package com.caucho.hessian.io;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by Jun on 2019-08-02.
 */
public class Hessian2OutputTest {

    Hessian2Output hessian2Output;
    ByteArrayOutputStream bytesOutputStream;

    Hessian2Input hessian2Input;
    ByteArrayInputStream byteArrayInputStream;

    HessianFactory hessianFactory;


    @Before
    public void before() {
        hessianFactory = new HessianFactory();
        hessian2Output = hessianFactory.createHessian2Output();
        bytesOutputStream = new ByteArrayOutputStream();
        hessian2Output.init(bytesOutputStream);

        byte[] buffer = new byte[1024];
        byteArrayInputStream = new ByteArrayInputStream(buffer);
        hessian2Input = hessianFactory.createHessian2Input(byteArrayInputStream);
    }


    @org.junit.Test
    public void writeNull() {
    }

    @org.junit.Test
    public void writeString() throws IOException {
        hessian2Output.writeString("hello");
        hessian2Output.flushBuffer();
        System.out.println(bytesOutputStream.toString());
    }

    @Test
    public void writeObject() throws IOException {
        User user = new User();
        user.setId(1);
        user.setName("JJ");
        Clazz clazz = new Clazz();
        clazz.setId(1);
        clazz.setName("2班");
        user.setClazz(clazz);
        // 写入类信息、字段个数、每个filed的value，参考UnsafeSerializer
        hessian2Output.writeObject(user);

        // 泛型Test
        Person<User> userPerson = new Person<>();
        userPerson.setA(user);
        userPerson.setId(1);
        hessian2Output.writeObject(userPerson);

        hessian2Output.flush();
        byte[] data = bytesOutputStream.toByteArray();


        // 反序列化
        hessian2Input = hessianFactory.createHessian2Input(new ByteArrayInputStream(data));
        User user1 = (User) hessian2Input.readObject(User.class);
        System.out.println(user1);

        // 泛型反序列化，支持
        Person person = (Person) hessian2Input.readObject(Person.class);
        System.out.println(person);






    }


}