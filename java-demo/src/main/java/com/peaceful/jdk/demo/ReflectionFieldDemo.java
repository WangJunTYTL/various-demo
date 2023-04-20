package com.peaceful.jdk.demo;

import com.peaceful.Util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/16
 * @since 1.6
 */

public class ReflectionFieldDemo {

    static class Hello {
        public String name = "hello";
        private int age = 28;
        public static int gender = 1;
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {

        //获取field 对象
        Field nameField = Hello.class.getDeclaredField("name");
        Util.report(nameField.get(new Hello()));
        Field ageField = Hello.class.getDeclaredField("age");
        ageField.setAccessible(true);
        Util.report(ageField.get(new Hello()));
        Field genderField = Hello.class.getDeclaredField("gender");
        Util.report(genderField.get(new Hello()));


//        Field[] fields = Hello.class.getFields(); // 不会返回私有变量
        Field[] fields = Hello.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Util.report(field.getName() + ":" + field.get(new Hello()));
        }
    }

}
