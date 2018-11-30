package com.peaceful.demo.mybatis;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * Created by wangjun38 on 2018/5/19.
 */
@Intercepts(
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
)
public class MyInterceptor2 implements Interceptor {

    public Object intercept(Invocation invocation) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("intercept2:start------------------");
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        String sql = mappedStatement.getBoundSql(invocation.getArgs()[1]).getSql();
        Object result = invocation.proceed();
        System.out.println("intercept2:sql["+sql+"] cost:["+(System.currentTimeMillis()-start)+"ms]");
        System.out.println("intercept2:end------------------");
        return result;
    }

    public Object plugin(Object target) {
        target = Plugin.wrap(target, this); // Plugin 是mybatis内置的封装类
        return target;
    }

    public void setProperties(Properties properties) {

    }
}
