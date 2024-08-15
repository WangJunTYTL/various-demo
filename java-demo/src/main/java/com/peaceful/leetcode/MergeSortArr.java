package com.peaceful.leetcode;

public class MergeSortArr {

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 6};
        int[] b = {5, 8};
        merge(a, b);
    }

    static void merge(int[] a, int[] b) {
        int x = 0;
        int y = 0;
        int maxLength = Math.max(a.length, b.length);
        int[] c = new int[a.length + b.length];
        int z = 0;
        while (x < a.length || y < b.length) {
            if (a[x] < b[y]) {
                c[z] = a[x];
                x++;
            } else {
                c[z] = b[y];
                y++;
            }
            z++;
            // 处理边界
            if(x == a.length && x<=maxLength){
                while (y < b.length) {
                    c[z] = b[y];
                    y++;
                    z++;
                }
            }
            if(y == b.length && y<=maxLength){
             while (x < a.length) {
                    c[z] = a[x];
                    x++;
                    z++;
                }
            }
        }
        for (int j : c) {
            System.out.println(j);
        }

    }
}
