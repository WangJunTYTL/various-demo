package com.peaceful.collection.demo.sort;

/**
 *
 * 冒泡排序:相邻元素比较,小的往后去
 *
 * 如下:
 *
 * | 1,3,2,6,8,5 |
 * ---------------
 * | 3,2,6,8,5,1 |
 * ---------------
 * | 3,6,8,5,2,1 |
 * ---------------
 * | 6,8,5,3,2,1 |
 * ---------------
 * | 8,6,5,3,2,1 |
 *
 * 时间复杂度:o(n^2)
 *
 * @author <a href="mailto:wangjuntytl@163.com">WangJun</a>
 * @version 1.0 15/12/8
 */
public class BubbleSort implements ISort{

    @Override
    public Integer[] sort(Integer[] data) {
        return new Integer[0];
    }
}
