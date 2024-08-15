package com.peaceful.leetcode;

public class ArrTargetValues {

    public static void main(String[] args) {
        int[] arr =  {1,2,3,4,5,6};
        test(arr,6);
    }

    private static void test(int[] arr, int target) {
        int[] index = new int[arr.length];
        int n = 0;
        // 查找比target小的值
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= target) {
                index[n] = i;
                n++;
            }
        }
        // 从第一个值开始循环递归遍历
        int y = 0;
        while (y < n) {
            int x = arr[index[y]];
            for (int m = y + 1; m < n; m++) {
                if (x + arr[index[m]] == target) {
                    System.out.println(index[y] + "," + index[m]);
                    break;
                }
            }
            y++;
        }
    }

}
