package com.peaceful.lock.demo;

import org.slf4j.helpers.Util;

/**
 * Created by wangjun on 16/8/24.
 */
public class T1 {

    public static void main(String[] args) {
        int x =1,y=2;
        x = y =3;
        Util.report(x+""+y);
    }
}
