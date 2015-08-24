package com.peaceful.cglib.demo;

import com.peaceful.common.util.Util;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/16
 * @since 1.6
 */

public class MyTask extends TaskSchedule<MyTask> {

    public String say(String str) {
        Util.report(str);
        return null;
    }
}
