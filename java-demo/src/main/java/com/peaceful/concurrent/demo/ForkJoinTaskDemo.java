package com.peaceful.concurrent.demo;

import com.peaceful.common.util.Util;

import java.util.concurrent.RecursiveAction;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/9/6
 * @since 1.6
 */

public class ForkJoinTaskDemo extends RecursiveAction {

    private int x = 0;

    public ForkJoinTaskDemo(int x) {
        this.x = x;
    }

    public void computeDirectly(int y) {
        Util.report(y);
    }

    @Override
    protected void compute() {

        if (x == 1) {
            computeDirectly(x);
            return;
        } else {
            ForkJoinTaskDemo f = new ForkJoinTaskDemo(x-1);
            f.fork();
            ForkJoinTaskDemo r = new ForkJoinTaskDemo(1);
            r.compute();
        }

    }
}
