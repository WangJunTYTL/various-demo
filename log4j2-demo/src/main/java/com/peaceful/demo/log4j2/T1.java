package com.peaceful.demo.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by wangjun38 on 2019-01-02.
 */
public class T1 {

    public static void main(String[] args) {
        Logger logger = LogManager.getLogger();
        logger.info("hello world");
    }
}
