package com.peaceful.other.string.search;

import com.peaceful.common.util.Util;

/**
 * Created by wangjun on 16/2/25.
 */
public class SimpleSearch {

    public static void main(String[] args) {

        SimpleSearch search = new SimpleSearch();
        String base = "abscwangdefgasaswang";
        String keys = "wang";
        search.search(base, keys);

    }

    /**
     * 每次遇到不同的字符串 向前移动1位
     *
     * @param base
     * @param keys
     */
    public void search(String base, String keys) {
        // Assert
        if (base == null || base.length() == 0 || keys == null || keys.length() == 0 || keys.length() > base.length()) {
            return;
        }

        int i = 0, j = 0,c=0;
        while (i + j < base.length() && j < keys.length()) {
            if (base.charAt(i + j) == keys.charAt(j)) {
                j++;
            } else {
                i++;
                j = 0;
            }
            c++;
        }

        if (j == keys.length()) {
            Util.report(keys + " Found from  [" + i + "]" + base+" loop "+c);
        } else {
            Util.report(keys + " Not Found from " + base+" loop "+c);
        }

    }

    // kmp算法
    public void kmpSearch(String base, String keys) {

    }


}
