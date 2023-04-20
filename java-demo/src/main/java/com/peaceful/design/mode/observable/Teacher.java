package com.peaceful.design.mode.observable;

import com.peaceful.Util;

import java.util.Observable;

/**
 * @author wangjun
 * @version 1.0
 * @since 15/5/8.
 */

public class Teacher extends Observable {

    public void set(String data) {
        Util.report("teacher say " + data);
        setChanged();
    }
}
