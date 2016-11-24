package com.peaceful.lock.demo;

import java.util.Stack;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by wangjun on 16/8/30.
 */
public class MyShardSync implements Lock {

    Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquireShared(1);
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
        sync.releaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    private class Sync extends AbstractQueuedSynchronizer {

        @Override
        protected int tryAcquireShared(int arg) {
            // 返回值 >= 0代表获取到锁
            for (; ; ) {
                int state = getState();
                if (state < 0) {
                    // 锁已经被获取了
                    return -1;
                } else if (state >= 0 && compareAndSetState(state, -1)) {
                    return 1;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            for (;;){
                int state = getState();

                if (state < 0 && compareAndSetState(state,0)){
                    return true;
                }
            }
        }
    }
}
