package com.peaceful.demo.rose.interceptors;

import com.peaceful.common.util.Util;
import net.paoding.rose.web.ControllerInterceptorAdapter;
import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Interceptor;

/**
 * Created by wangjun on 14/12/8.
 * <p/>
 * before:在controller执行前执行。
 * after:在controller执行中(后)执行,如果一个返回抛出了异常,则不会进来。
 * afterCompletion:在controller执行后执行,不论是否异常,都会进来。
 * isForAction:定义满足某条件的才会被拦截。
 *
 * 不需要配置，但必须放在controllers包下
 */
@Interceptor
public class AccessTraceInterceptor extends ControllerInterceptorAdapter {


    @Override
    protected Object before(Invocation inv) throws Exception {
        Util.report("-------------------------------------");
        Util.report(inv.getMethod().getName());
        Util.report("-------------------------------------");
        return super.before(inv);
    }
}
