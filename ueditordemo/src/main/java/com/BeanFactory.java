package com;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

/**
 * Date 14/10/23.
 * Author WangJun
 * Email wangjuntytl@163.com
 */
public class BeanFactory {

    private static   ApplicationContext applicationContext = null;


    public static void init(){
        applicationContext =new ClassPathXmlApplicationContext("classpath*:spring/applicationContext*.xml");
    }

    public static <T> T getBean(Class<T> requiredType){
        return applicationContext.getBean(requiredType);
    };
}
