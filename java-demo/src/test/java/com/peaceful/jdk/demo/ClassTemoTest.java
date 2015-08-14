package com.peaceful.jdk.demo;

import org.junit.Before;
import org.junit.Test;

import java.beans.Introspector;

public class ClassTemoTest {

    ClassDemo aClass;

    @Before
    public void setUp() throws Exception {
        aClass = new ClassDemo();
    }

    @Test
    public void testForName() throws Exception {
        aClass.forName();
    }

    @Test
    public void testA() {
        for (int i = 0; i < 10; i++) {
            System.out.println((Integer) i);
        }
    }

    @Test
    public void aa() {
    }

}