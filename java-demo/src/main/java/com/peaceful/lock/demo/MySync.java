package com.peaceful.lock.demo;

import java.util.Stack;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by wangjun on 16/8/30.
 */
public class MySync implements Lock {

    Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {

        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    private class Sync extends AbstractQueuedSynchronizer {

        @Override
        protected boolean tryAcquire(int arg) {
            for (; ; ) {
                int state = getState();// >= 0 代表可以去获取同步锁
                if (state >= 0) {
                    compareAndSetState(state, -1);
                    // 当前线程获取到锁,且不允许其它线程在获取到锁
                    return true;
                }
                if (state < 0 ){
                    return false;
                }
            }
        }

        @Override
        protected boolean tryRelease(int arg) {
            for (;;) {
                int state = getState();
                if (state < 0 && compareAndSetState(state, 0)) {
                    return true;
                } else if (state >= 0) {
                    return true;
                }
            }
        }

    }
}
