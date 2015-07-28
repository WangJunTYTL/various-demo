package com.peaceful.other;

/**
 * <a mailto:wangjuntytl@163.com>Email:wangjuntytl@163.com</a>
 * 八皇后问题
 *
 * @author wangjun
 * @version 1.0
 * @since 15/4/11.
 */

public class Queen {

    //同栏是否有皇后，1表示有
    private int[] column;

    //右上至左下是否有皇后
    private int[] rup;

    //左上至右下是否有皇后
    private int[] lup;

    //解答
    private int[] queen;

    //解答编号
    private int num;

    public Queen() {
        column = new int[8 + 1];
        rup = new int[(2 * 8) + 1];
        lup = new int[(2 * 8) + 1];

        for (int i = 1; i <= 8; i++)
            column[i] = 1;

        for (int i = 1; i <= (2 * 8); i++)
            rup[i] = lup[i] = 1;

        queen = new int[8 + 1];
    }

    public void backtrack(int i) {
        if (i > 8) {
            showAnswer();
        } else {
            for (int j = 1; j <= 8; j++) {
                if ((column[j] == 1) && (rup[i + j] == 1) &&
                        (lup[i - j + 8] == 1)) {
                    queen[i] = j;
                    //设定为占用
                    column[j] = rup[i + j] = lup[i - j + 8] = 0; // 递归
                    backtrack(i + 1);
                    column[j] = rup[i + j] = lup[i - j + 8] = 1;  //回溯
                }
            }
        }
    }

    protected void showAnswer() {
        num++;
        System.out.println("\n解答" + num);

        for (int y = 1; y <= 8; y++) {
            for (int x = 1; x <= 8; x++) {
                if (queen[y] == x) {
                    System.out.print("Q");
                } else {
                    System.out.print(".");
                }
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        Queen queen = new Queen();
        queen.backtrack(1);
    }
}