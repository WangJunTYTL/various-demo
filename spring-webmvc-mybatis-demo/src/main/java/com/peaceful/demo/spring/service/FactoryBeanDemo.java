package com.peaceful.demo.spring.service;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * Created by wangjun on 16/3/2.
 */
@Component
public class FactoryBeanDemo implements FactoryBean<DBConn> {

    @Override
    public DBConn getObject() throws Exception {
        return new DBConn();
    }

    @Override
    public Class<?> getObjectType() {
        return DBConn.class;
    }

    @Override
    public boolean isSingleton() {
        return true; // 如果是单例，则getObject方法只调用一次
    }
}
