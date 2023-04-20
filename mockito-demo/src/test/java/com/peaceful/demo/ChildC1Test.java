package com.peaceful.demo;

import org.junit.Test;
import org.mockito.Mockito;

/**
 * Created by wangjun38 on 2020/7/25.
 */
public class ChildC1Test {

    @Test
    public void test(){
        ChildC1 childC1 = Mockito.spy(ChildC1.class);
        Mockito.doReturn("Hi").when(childC1).sayHi(Mockito.anyString());
    }

    @Test
    public void test2(){
        ChildC1 childC1 = Mockito.spy(ChildC1.class);
        Mockito.doReturn("Hi").when(childC1).say(Mockito.anyString());
        childC1.say(null);
    }
}
