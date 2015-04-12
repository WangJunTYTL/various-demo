package com.peaceful.thread.demo;

import com.peaceful.common.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * <a mailto:wangjuntytl@163.com>Email:wangjuntytl@163.com</a>
 *
 * @author wangjun
 * @version 1.0
 * @since 15/4/11.
 */

public class T1 {

    public static void main(String[] args) {
        t1();
    }

    public static List t1() {
        List list = new ArrayList();
        for (int x = 1; x < 9; x++) {
            for (int y = 1; y < 9; y++) {
                list.add(x + "" + y);
            }
        }
        return list;
    }

}
