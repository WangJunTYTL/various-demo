package com.caucho.hessian.io;

import java.io.Serializable;

/**
 * Created by Jun on 2019-08-08.
 */
public class Person<T> implements Serializable {

    private int id;
    private T a;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public T getA() {
        return a;
    }

    public void setA(T a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", a=" + a +
                '}';
    }
}
