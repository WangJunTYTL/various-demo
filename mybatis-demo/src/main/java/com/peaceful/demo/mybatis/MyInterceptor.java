package com.peaceful.demo.mybatis;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;

import java.util.Properties;

/**
 * Created by wangjun38 on 2018/5/19.
 */
public class MyInterceptor implements Interceptor {

    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("intercept------------------");
        return invocation.proceed();
    }

    public Object plugin(Object target) {
        System.out.println("plugin:" + target.getClass());
        if (target instanceof StatementHandler) {
            StatementHandler statementHandler = (StatementHandler) target;
            String sql = statementHandler.getBoundSql().getSql();
            Object parameterObject = statementHandler.getBoundSql().getParameterObject();
            System.out.println("sql:" + sql);
            System.out.println("params:" + parameterObject);
        }
        return target;
    }

    public void setProperties(Properties properties) {

    }
}
