package com.peaceful.lock.demo;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/9/5
 * @since 1.6
 */

public class AQSDemo {

    private class Sync extends AbstractQueuedSynchronizer{

        //
        @Override
        protected int tryAcquireShared(int arg) {

            return (getState() == 1) ? 1: -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            setState(1);
            return true;
        }
    }

    private final Sync sync = new Sync();

    public void signal()  {
        sync.releaseShared(0);
    }

    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(0);
    }
}
