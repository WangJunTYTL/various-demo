package com.peaceful.serializable.demo;

import java.util.List;

/**
 * @author WangJun
 * @version 1.0 16/4/15
 */
public interface Hello {


    public void say(List<User> userCycleQueue);
    public void say(CycleQueue<User> userCycleQueue);
}
