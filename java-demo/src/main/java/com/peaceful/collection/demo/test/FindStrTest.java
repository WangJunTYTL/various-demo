package com.peaceful.collection.demo.test;

/**
 * Created by JunWang on 2016/12/14.
 */
public class FindStrTest {

    private static int indexOf(String s1, String s2) {
        if (s1.length() < s2.length()){
            return -1;
        }

        // 找到第一个相等的字符串索引i

        // 找到第一个相等的字符串后，从该位置查找与s2等长的位置，从末尾开始比较 ,如果全部相等，则找到，如果出现不等于，则找到下一个字符串相等的位置重复执行

        // 跳出的条件是：i+s2.length-1 < s1.length-1  ， 找到了满足的条件
        char first = s2.charAt(0);

        for (int i = 0;i+s2.length() < s1.length();i++){ // 满足跳出条件

            while (s1.charAt(i) != first){i++;} // 实现第一步

            int end = i+s2.length()-1; // 需要比较到区间范围，注意应该是<=end

            if (end > s1.length()-1){ // 校验end是否越界，如果越界 则跳出
                return -1;
            }

            int n = 1;
            for (int j = i+1;j<=end && n < s2.length();j++,n++){
                if (s1.charAt(j) != s2.charAt(n)){
                    i++;
                    break;
                }
            }

            if (n == s2.length()){ // 比较所有的位后，符合匹配规则
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.print(indexOf("acedbecbc","bc"));
    }
}
