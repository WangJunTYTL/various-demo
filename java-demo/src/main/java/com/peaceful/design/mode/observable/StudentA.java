package com.peaceful.design.mode.observable;

import com.peaceful.common.util.Util;

import java.util.Observable;
import java.util.Observer;

/**
 * @author wangjun
 * @version 1.0
 * @since 15/5/8.
 */

public class StudentA implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        Util.report("a receive inform..." + arg);
    }
}
