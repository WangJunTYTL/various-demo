package com.peaceful.jdk.demo;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClassTemoTest {

    ClassTemo aClass;

    @Before
    public void setUp() throws Exception {
        aClass = new ClassTemo();
    }

    @Test
    public void testForName() throws Exception {
        aClass.forName();
    }
}