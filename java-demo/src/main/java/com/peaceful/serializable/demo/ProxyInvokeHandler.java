package com.peaceful.serializable.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.peaceful.Util;

import java.lang.reflect.*;

/**
 * @author WangJun
 * @version 1.0 16/4/15
 */
public class ProxyInvokeHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class zClass = method.getDeclaringClass();
        Class[] parameterTypes = method.getParameterTypes();
        Task task = new Task();
        task.zClass = zClass;
        task.method = method.getName();
        task.args = args;
        task.paramterTypes = parameterTypes;
        SerializerFeature feature = SerializerFeature.DisableCircularReferenceDetect;
        Util.report(JSON.toJSONString(task,feature));
        return null;
    }
}
