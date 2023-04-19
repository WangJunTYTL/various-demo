package com.peaceful.collection.demo.sort;

import com.peaceful.Util;

/**
 * 快速排序：然后将所有比它小的数都放到它前面，所有比它大的数都放到它后面，这个过程称为一趟快速排序。
 * 值得注意的是，快速排序不是一种稳定的排序算法，也就是说，多个相同的值的相对位置也许会在算法结束时产生变动。
 * <p>
 * Created by JunWang on 2016/11/24.
 */
public class QuickSort {


    public static void main(String[] args) {

        Util.report(sort(DataSample.integers,DataSample.integers.clone(),0,DataSample.integers.length-1));
    }

    // 没有实现
    private static Integer[] sort(Integer[] data, Integer[] dest,int from, int to) {
        // 这总是有序的
        if (data == null || data.length <= 1) {
            return data;
        }
        if (to - from <= 1) {
            return data;
        }

        int mid = from+((to - from) / 2);


        int n1 = from, n2 = to;
        for (int n = n1; n <= to; n++) {
            if (data[n] <= data[mid]) {
                // 小的放在左边
                dest[n1] = data[n];
                n1++;
            } else {
                // 大的放右边
                dest[n2] = data[n];
                n2--;
            }
        }

        // 开始第二次排序
        sort(dest, dest,from, n1-1);
        sort(dest, dest,n2+1,to);
        return dest;
    }

}
