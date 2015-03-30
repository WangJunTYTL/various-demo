package com.peaceful.cglib.demo;

import org.junit.Before;
import org.junit.Test;

public class ProxySourceTest {

    ProxySource<MyBeanHandle> proxySource;

    @Before
    public void befor(){
        proxySource = new CglibProxySource<MyBeanHandle>(MyBeanHandle.class);
    }


    @Test
    public void testCreateProxy() throws Exception {


    }

    @Test
    public void testResolveProxy() throws Exception {

    }
}