package com.peaceful.sync.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by wangjun on 16/9/2.
 */
public class MySync implements Lock {

    private Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(-1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.release(0);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(-1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.release(0);
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    private class Sync extends AbstractQueuedSynchronizer {

        @Override
        protected boolean tryAcquire(int arg) {
            // state 初始值 0 ，这里用大于0表示获取到锁，小于等于0表示锁是空闲的
            for (; ; ) {
                int state = getState();
                if (state <= 0) {
                    if (compareAndSetState(state, 1))
                        return true;
                } else {
                    return false;
                }
            }
        }

        @Override
        protected boolean tryRelease(int arg) {
            for (; ; ) {
                int state = getState();
                if (state <= 0) {
                    throw new IllegalMonitorStateException();
                } else  {
                    if (compareAndSetState(state, 0)) {
                        return true;
                    }
                }
            }
        }
    }
}
