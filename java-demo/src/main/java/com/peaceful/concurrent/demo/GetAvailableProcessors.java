package com.peaceful.concurrent.demo;


import com.peaceful.common.util.Util;

/**
 * 获取机器可利用的线程个数
 *
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/10/16
 * @since 1.6
 */

public class GetAvailableProcessors {

    public static void main(String[] args) {

        Util.report(Runtime.getRuntime().availableProcessors());
    }
}
