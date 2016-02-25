package com.peaceful.demo.guice;

import com.google.inject.Inject;

/**
 * Created by wangjun on 16/2/20.
 */
public class Test2 {


    @Inject
    Test test;

    public void hello (String aa){
        test.hello(aa);
    }
}
