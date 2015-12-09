package com.peaceful.proxy.demo.test;

import com.peaceful.proxy.demo.Say;

import java.lang.reflect.InvocationTargetException;

/**
 * @author <a href="mailto:wangjuntytl@163.com">wangjun</a>
 * @version 1.0 15/12/5
 */
public class InvokeTest {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        InvokeMeta meta = new InvokeMeta();
        meta.claszz = Say.class;
        meta.method = "say";
        meta.parameterizedTypes = null;
        meta.args = null;

        Invoker invoke = new InvokerImpl();
        // 模拟调用
        System.out.println(invoke.doInvoke(meta));

        // 模拟远程调用
        Say say = ServiceFactoryDemo.lookup(Say.class);
        System.out.println(say.say());
    }
}
