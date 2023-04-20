package com.peaceful.reflection;

import java.lang.reflect.Field;

public class T1 {

    public static void main(String[] args) {
        User user = new User();
        Field field = null;
        try {
            field = User.class.getDeclaredField("name");
            field.setAccessible(true);
            field.set(user, "name");
            field.setAccessible(false);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        System.out.println(user.getId());
        System.out.println(user.getName());

        setPrivateField(user, "name","LL" );
        System.out.println(user.getName());
    }

    public static void setPrivateField(Object instance, String fileName, Object value) {
        Field field = null;
        try {
            field = instance.getClass().getDeclaredField(fileName);
            field.setAccessible(true);
            field.set(instance, value);
            field.setAccessible(false);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
