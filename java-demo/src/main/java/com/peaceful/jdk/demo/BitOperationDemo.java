package com.peaceful.jdk.demo;

import com.peaceful.common.util.Util;

/**
 * <a mailto:wangjuntytl@163.com>Email:wangjuntytl@163.com</a>
 *
 * @author wangjun
 * @version 1.0
 * @since 15/4/11.
 */

public class BitOperationDemo {

    public static void main(String[] args) {

        //<<表示左移, 左移一位表示原来的值乘2.
        //例如：3 <<2(3为int型)
        //1）把3转换为二进制数字0000 0000 0000 0000 0000 0000 0000 0011，
        //2）把该数字高位(左侧)的两个零移出，其他的数字都朝左平移2位，
        //3）在低位(右侧)的两个空位补零。则得到的最终结果是0000 0000 0000 0000 0000 0000 0000 1100，
        //转换为十进制是12。
        //同理,>>表示右移. 右移一位表示除2.
        Util.report("位运算");
        Util.dashed();
        Util.report(3 << 2); // 运算规则：3转为二进制 ，然后高位移除两位，剩余向前平移2位，低位补0 ，结果为12
        Util.report(3 >> 2); // print 0


       //位运算:

       //位运算符包括:　与（&）、非（~）、或（|）、异或（^）

       //　　&：当两边操作数的位同时为1时，结果为1，否则为0。如1100&1010=1000 　　

       //| ：当两边操作数的位有一边为1时，结果为1，否则为0。如1100|1010=1110 　　

       //~：0变1,1变0 　　

       //^：两边的位不同时，结果为1，否则为0.如1100^1010=0110

        Util.report(5^5);
        Util.report(0101^1010);



    }
}
