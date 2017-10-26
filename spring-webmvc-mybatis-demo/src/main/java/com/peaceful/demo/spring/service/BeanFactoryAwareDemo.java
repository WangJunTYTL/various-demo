package com.peaceful.demo.spring.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * Created by wang on 2017/7/4.
 */
@Component
public class BeanFactoryAwareDemo implements BeanFactoryAware {
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.err.println("BeanFactoryAwareDemo-->execute");
    }
}
