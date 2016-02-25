package com.peaceful.demo.guice;

import com.google.inject.Inject;

/**
 * Created by wangjun on 16/2/19.
 */
public class ChargeBussines {

    TraceLogI traceLogI;

    /**
     * 可以注入属性、构造方法、其它方法也行
     **/
    @Inject
    public ChargeBussines(TraceLogI traceLogI) {
        this.traceLogI = traceLogI;
    }

    public boolean charge(double money) {
        traceLogI.log(String.valueOf(money));
        return true;
    }


}
