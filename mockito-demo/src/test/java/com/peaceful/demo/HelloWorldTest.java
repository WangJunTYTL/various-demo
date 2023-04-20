package com.peaceful.demo;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.spy;

/**
 * Mockito: 所有的api方法
 * BDDMockito: 对Mockito的api进行了封装
 *
 *
 * Created by wangjun38 on 2019-11-20.
 */
class HelloWorldTest {

    @Test
    void say() {
        HelloWorld helloWorld = Mockito.mock(HelloWorld.class);

        given(helloWorld.say(anyString())).willReturn("嗨");

        assertEquals("嗨", helloWorld.say("Hi"));

        HelloWorld helloWorld1 = new HelloWorld();

        HelloWorld helloWorld2 = spy(helloWorld1);

        given(helloWorld2.say(anyString())).willReturn("Hi");

        assertEquals("Hi", helloWorld2.say("Nothing"));

        assertEquals("Hi", helloWorld2.say("What"));
    }

    @Test
    public void mockApi() {
        // mock实例不会真是的调用方法，方法调用会返回null这种
        HelloWorld helloWorld = Mockito.mock(HelloWorld.class);
        String result = helloWorld.say("hi");
        assertEquals(null, result);
    }


    @Test
    public void spyApi() {
        // 使用无参构造方法创建一个实例
        HelloWorld helloWorld = Mockito.spy(HelloWorld.class);
        String result = helloWorld.say("hi");
        assertEquals("hi", result);
    }

    @Test
    public void whenAPi() {
        HelloWorld helloWorld = Mockito.spy(HelloWorld.class);
        // say方法会执行,(helloWorld.say(Mockito.anyString())方法执行了，并不是下面的一行实际执行了say方法)
        Mockito.when(helloWorld.say(Mockito.anyString())).thenReturn("HiHi");
        String result = helloWorld.say("Hi");
        assertEquals("HiHi", result);
        // say方法不会执行
        Mockito.doReturn("Hi").when(helloWorld).say(anyString());
        result = helloWorld.say("Hello");
        assertEquals("Hi", result);
    }

    @Test
    public void whenAPi2() {
        HelloWorld helloWorld = Mockito.spy(HelloWorld.class);
        // say方法不会执行
        Mockito.doReturn("Hi").when(helloWorld).say(anyString());
        String result = helloWorld.say("Hello");
        assertEquals("Hi", result);
    }


}