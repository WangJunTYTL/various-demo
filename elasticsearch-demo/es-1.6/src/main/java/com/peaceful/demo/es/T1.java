package com.peaceful.demo.es;

import org.slf4j.helpers.Util;

/**
 * Created by wangjun on 16/2/11.
 */
public class T1 {

    public static void main(String[] args) {
        char[] up = new char[26];
        for (int n = 65;n<65+26;n++){
            up[n-65] = (char)n;
        }
        char[] low = new char[26];
        for (int n = 97;n<97+26;n++){
            low[n-97] = (char)n;
        }

        Util.report(new String(up));
        Util.report(new String(low));
    }
}
