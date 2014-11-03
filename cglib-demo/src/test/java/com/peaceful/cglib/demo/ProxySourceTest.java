package com.peaceful.cglib.demo;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProxySourceTest {

    ProxySource<com.peaceful.cglib.demo.Test> proxySource;

    @Before
    public void befor(){
        proxySource = new CglibProxySource<com.peaceful.cglib.demo.Test>(com.peaceful.cglib.demo.Test.class);
    }


    @Test
    public void testCreateProxy() throws Exception {

        com.peaceful.cglib.demo.Test test = proxySource.createProxy(new TestImpl(),new UsageTrackingImp());
        test.say();

    }

    @Test
    public void testResolveProxy() throws Exception {

    }
}