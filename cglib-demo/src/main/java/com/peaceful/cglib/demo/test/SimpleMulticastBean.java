package com.peaceful.cglib.demo.test;

/**
 * @author wangjun
 * @version 1.0
 * @since 15/4/28.
 */

public class SimpleMulticastBean implements DelegatationProvider {
    private String value;
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}
