package com.peaceful.leedcode.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 01.TwoNum.md
 * https://leetcode.com/problems/two-sum/#/solutions
 * Created by wang on 2017/3/24.
 */
public class TwoNum {

    private static final Integer[] arr = new Integer[]{1, 5, 7, 8, 9, 6};

    public static void main(String[] args) {
        System.out.println(find(6));
        System.out.println(find(8));
        System.out.println(find(16));

        System.out.println(find2(6));
        System.out.println(find2(8));
        System.out.println(find2(16));
    }

    // 这里不是最优方案 最优方案是放入到HashMap中处理
    private static String find(int target) {
        List<Integer> list = new ArrayList<Integer>();
        Map<Integer, Integer> valueIndex = new HashMap<Integer, Integer>();
        // 确定可能值范围
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < target) {
                list.add(arr[i]);
                valueIndex.put(arr[i], i);
            }
        }
        // 把可能的值进行排序
        Collections.sort(list);
        int i = 0, j = list.size() - 1;
        // 从左开始尝试  从两头开始尝试计算
        while (i < list.size() - 1 && j > 0 && j > i) {
            int result = list.get(i) + list.get(j);
            if (result < target) {
                // 需要i+1向前看 ，同时j复位
                ++i;
                j = list.size() - 1;
            } else if (result > target) {
                --j;
            } else if (result == target) {
                return "{" + valueIndex.get(list.get(i)) + "," + valueIndex.get(list.get(j)) + "}";
            }
        }
        return null;
    }

    // 最优解
    private static String find2(int target) {
        Map<Integer, Integer> reverseIndex = new HashMap<Integer, Integer>();
        for (int i = 0; i < arr.length; i++) {
            reverseIndex.put(arr[i], i);
        }
        for (int i = 0; i < arr.length; i++) {
            if (reverseIndex.containsKey(target-arr[i])){
                return "{"+i+","+reverseIndex.get(target-arr[i])+"}";
            }
        }
        return null;
    }
}
