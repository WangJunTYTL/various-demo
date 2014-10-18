package com.peaceful.activemq;

import com.peaceful.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;

/**
 * Date 14-10-16.
 * Author WangJun
 * Email wangjuntytl@163.com
 */
public class Test {

    public static void  main(String[] args){
        Logger logger = LoggerFactory.getLogger(Test.class);
        Util.report(StaticLoggerBinder.REQUESTED_API_VERSION);
        logger.debug("hello world");
    }
}
