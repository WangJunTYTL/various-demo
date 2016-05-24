package com.peaceful.serializable.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.peaceful.common.util.Util;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * @author WangJun
 * @version 1.0 16/4/15
 */
public class HelloDemo {

    public static void main(String[] args) throws NoSuchMethodException {
        String json = "{\"args\":[[{\"name\":\"01\"},{\"name\":\"02\"}]],\"method\":\"say\",\"paramterTypes\":[\"java.util.List\"],\"zClass\":\"com.peaceful.serializable.demo.Hello\"}";
        Task task = JSON.parseObject(json,Task.class);
        Class zClass = task.zClass;
        Method method = zClass.getMethod(task.method,task.paramterTypes);
        Type[] types = method.getGenericParameterTypes();
        for (int i=0;i<types.length;i++){
            Util.report(types[i]);
            SerializerFeature feature = SerializerFeature.DisableCircularReferenceDetect;
            task.args[i] = JSON.parseObject(JSON.toJSONString(task.args[i]),types[i]);
            Util.report(task.args[i]);
        }
    }
}
