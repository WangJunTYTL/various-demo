package com.caucho.hessian.io;

import java.io.Serializable;

/**
 * Created by Jun on 2019-08-05.
 */
public class User implements Serializable {

    private int id;
    private String name;
    private Clazz clazz;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", clazz=" + clazz +
                '}';
    }
}
