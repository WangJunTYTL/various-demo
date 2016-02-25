package com.peaceful.demo.guice;

import com.google.inject.Inject;

/**
 * Created by wangjun on 16/2/20.
 */
public class Test4 {



    @Inject
    Test3 test3;

    public void hello(String aa){
        test3.hello(aa);
    }
}
