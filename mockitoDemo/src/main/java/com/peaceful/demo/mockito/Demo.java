package com.peaceful.demo.mockito;

import org.mockito.BDDMockito;
import org.mockito.MockedStatic;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mockStatic;

public class Demo {

    public static void main(String[] args) {
        //使用文档 https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html
        // mock静态方法
        try (MockedStatic mocked = mockStatic(User.class)) {
            mocked.when(User::getName).thenReturn("bar");
            assertEquals("bar", User.getName());
            mocked.verify(User::getName);
        }
        assertEquals("LL", User.getName());


        List<Integer> mockedList = BDDMockito.mock(List.class);
        mockedList.add(1);
        // 验证该方法且传参是1的情况被调用1次
        BDDMockito.verify(mockedList).add(1);


    }
}
