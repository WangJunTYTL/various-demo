package com.peaceful.jdk.demo;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/10/26
 * @since 1.6
 */

public class User {

    public long id;
    public String name;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id == user.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
