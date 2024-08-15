package com.peaceful.leetcode;

public class PalindromicSubstringsCount {

    public static int countPalindromicSubstrings(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int count = 0;

        // 单个字符是回文
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
            count++;
        }

        // 两个连续相同字符是回文
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                count++;
            }
        }

        // 长度大于 2 的子串
        for (int len = 3; len <= n; len++) {
            for (int start = 0; start <= n - len; start++) {
                int end = start + len - 1;
                if (s.charAt(start) == s.charAt(end) && dp[start + 1][end - 1]) {
                    dp[start][end] = true;
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        String str = "aba";
        System.out.println(countPalindromicSubstrings(str));
    }
}
