package com.caucho.hessian.io;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import static org.junit.Assert.*;

/**
 * Created by Jun on 2019-08-06.
 */
public class Hessian2InputTest {

    Hessian2Input hessian2Input;

    @Before
    public void before(){

        HessianFactory hessianFactory = new HessianFactory();
        byte[] buffer = new byte[1024];
        hessian2Input = hessianFactory.createHessian2Input(new ByteArrayInputStream(buffer));

    }

    @Test
    public void readObject() throws IOException {
        User user = (User) hessian2Input.readObject(User.class);
    }
}