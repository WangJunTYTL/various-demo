package com.peaceful.nio.demo;

import java.io.IOException;
import java.nio.channels.Selector;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/26
 * @since 1.6
 */

public class SelectorDemo {

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
    }
}
