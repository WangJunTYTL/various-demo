package com.peaceful.leetcode;

public class NumReverse {
    public static void main(String[] args) {
        System.out.println(reverse(156));
        System.out.println(reverse(-156));

    }

    static String reverse(int num) {
        String res = "";
        if (num >= 0 && num < 10) {
            res += num;
        } else if (num >= 10) {
            while (num > 10) {
                int lastNum = num % 10;
                num = num / 10;
                res += lastNum;
                if (num < 10) {
                    res += num;
                }
            }
        } else {
            res = "-" + reverse(Math.abs(num));
        }
        return res;
    }
}