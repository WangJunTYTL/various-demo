package com.peaceful.collection.demo.test;

/**
 * Created by JunWang on 2016/12/14.
 */
public class MaxTest {

    private static Integer[] arr = new Integer[]{1, 3, 5, 7, 8, 9, 6, 3, 2, 1};

    private static int max(Integer[] arr, int from, int to) {
        if (from == to){
            return arr[from];
        }
        if ((from+1) == to){
            if (arr[from] < arr[to]){
                return arr[to];
            }else {
                return arr[from];
            }
        }
        int mid = (from + to) / 2;
        if (arr[mid] < arr[mid+1]){
            return max(arr,mid+1,to);

        }else {
            return max(arr,from,mid);
        }
    }

    public static void main(String[] args) {
        System.out.println(max(arr,0,arr.length-1));
    }
}
