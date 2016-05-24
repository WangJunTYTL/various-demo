package com.peaceful.demo.spring.service;

import com.peaceful.common.util.Util;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author WangJun
 * @version 1.0 16/4/19
 */
@Component
public class BeanPostProcessorDemo implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Util.report("postProcessBeforeInitialization->"+beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Util.report("postProcessAfterInitialization->"+beanName);
        return bean;
    }
}
