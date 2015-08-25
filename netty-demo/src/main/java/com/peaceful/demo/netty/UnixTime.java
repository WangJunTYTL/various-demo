package com.peaceful.demo.netty;

import java.util.Date;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/25
 * @since 1.6
 */

public class UnixTime {

    private final long value;

    public UnixTime() {
        this(System.currentTimeMillis() / 1000L + 2208988800L);
    }

    public UnixTime(long value) {
        this.value = value;
    }

    public long value() {
        return value;
    }

    @Override
    public String toString() {
        return new Date((value() - 2208988800L) * 1000L).toString();
    }
}