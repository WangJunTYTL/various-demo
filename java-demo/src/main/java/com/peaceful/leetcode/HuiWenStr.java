package com.peaceful.leetcode;

public class HuiWenStr {


    public static void main(String[] args) {
        String str = "ababa12345654321";
//        String str = "werabawer";
        char[] charArr = str.toCharArray();
        search(charArr);
    }

    static void search(char[] charArr) {
        int m = 0;
        int n = charArr.length - 1;
        while (n - m > 1) {
            leftSearch(getSubArr(charArr, m, n));
            rightSearch(getSubArr(charArr, m, n));
            m++;
            n--;
        }
    }

    static void leftSearch(char[] charArr) {
        int i = 0;
        int j = charArr.length - 1;

        int m = i;
        int n = j;
        while (j - i > 1) {
            if (charArr[i] == charArr[j]) {
                // nothing
                i++;
                j--;
            } else {
                // fail
                n--;
                leftSearch(getSubArr(charArr, m, n));
                break;
            }
            if (j - i == 1 && charArr[i] == charArr[j]) {
                System.out.println(charArr);
            } else if (j == i) {
                System.out.println(charArr);
            }
        }
    }

    static void rightSearch(char[] charArr) {

        int i = 0;
        int j = charArr.length - 1;

        int m = i;
        int n = j;
        while (j - i > 1) {
            if (charArr[i] == charArr[j]) {
                // nothing
                i++;
                j--;
            } else {
                // fail
                m++;
                rightSearch(getSubArr(charArr, m, n));
                break;
            }
            if (j - i == 1 && charArr[i] == charArr[j]) {
                System.out.println(charArr);
            } else if (j == i) {
                System.out.println(charArr);
            }
        }
    }

    static char[] getSubArr(char[] charArr, int m, int n) {
        char[] subArr = new char[n - m + 1];
        for (int i = 0; i < subArr.length; i++) {
            subArr[i] = charArr[m];
            m++;
        }
        return subArr;
    }


}
