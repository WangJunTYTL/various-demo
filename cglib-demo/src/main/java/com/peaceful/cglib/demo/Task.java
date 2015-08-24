package com.peaceful.cglib.demo;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/16
 * @since 1.6
 */

public class Task {

    public String queueName;
    public Class aClass;
    public String methodName;
    public Class[] parameterTypes;
    public Object[] args;
    public String ext;


    @Override
    public String toString() {
        return "queueName:"+queueName+" Class:"+aClass.getName()+" ext"+ext;
    }
}
