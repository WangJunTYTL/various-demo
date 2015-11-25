package com.peaceful.pool.demo;

import org.slf4j.LoggerFactory;

/**
 * common-pool2 使用方式
 * <p/>
 * 假设这是一个建立TCP连接的对象，该对象的初始化时间平均为500ms，为了避免在程序中频繁创建Conn对象，我们需要借助对象池管理Conn对象实例
 *
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/10/28
 * @since 1.6
 */

public class Conn {

    /**
     * 记录对象的创建时间
     */
    private long createTime;


    /**
     * 初始化Conn对象，模拟创建Conn对象平均消耗500ms
     * @throws InterruptedException
     */
    public Conn() throws InterruptedException {
        Thread.sleep(500);
        createTime = System.currentTimeMillis();
        LoggerFactory.getLogger(getClass()).debug(" init conn suc... " + createTime);
    }

    /**
     * 报告Conn对象信息
     */
    public void report() {
        LoggerFactory.getLogger(getClass()).info("this is a available conn " + createTime);
    }
}
