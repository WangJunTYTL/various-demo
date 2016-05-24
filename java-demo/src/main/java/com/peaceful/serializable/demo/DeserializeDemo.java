package com.peaceful.serializable.demo;

import com.peaceful.common.util.Util;

import java.io.IOException;

/**
 * @author WangJun
 * @version 1.0 16/4/15
 */
public class DeserializeDemo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        CycleQueue<User> userCycleQueue = new CycleQueue<User>(8);
        userCycleQueue = (CycleQueue<User>) SerializableUtil.deserialize(userCycleQueue.getClass());
        Util.report(userCycleQueue.get());
    }

}
