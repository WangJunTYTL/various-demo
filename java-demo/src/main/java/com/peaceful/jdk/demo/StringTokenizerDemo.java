package com.peaceful.jdk.demo;

import com.peaceful.common.util.Util;

import java.util.StringTokenizer;

/**
 * 字符串分词器
 * <p/>
 * Created by wangjun on 15/3/28.
 */
public class StringTokenizerDemo {

    public static void main(String[] args) {
        String str = "hello,;world,:Zh:中国";
        StringTokenizer stringTokenizer = new StringTokenizer(str, ",;:");// 任何一个字符都是分割符
        while (stringTokenizer.hasMoreTokens()) {
            Util.report(stringTokenizer.nextToken());
        }
    }
}
