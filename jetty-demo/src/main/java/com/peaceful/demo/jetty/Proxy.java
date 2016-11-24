package com.peaceful.demo.jetty;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.http.message.BasicNameValuePair;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by wangjun on 2016/11/12.
 */
public class Proxy {

    public static <T> T getProxy(final Class<T> zlass) {
        return (T) java.lang.reflect.Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{zlass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Request request = new Request();
                request.zlass = zlass;
                request.args = args;
                request.method = method.getName();
                request.paramsType = method.getParameterTypes();
                request.requestTime = System.currentTimeMillis();
                BasicNameValuePair basicNameValuePair = new BasicNameValuePair("invoke_info",JSON.toJSONString(request));
                String res  = com.peaceful.demo.jetty.HttpClient.post("http://127.0.0.1:8081", Lists.newArrayList(basicNameValuePair));
                Response response = JSON.parseObject(res,Response.class);
                if (response.isHasException){
                    throw response.e;
                }
                return JSON.parseObject(response.data,method.getReturnType());
            }
        });
    }
}
