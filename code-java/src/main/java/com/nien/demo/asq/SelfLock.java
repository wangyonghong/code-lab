package com.nien.demo.asq;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class SelfLock implements Lock {

    //state 表示锁的状态
    // state=1 获取到了锁，state=0，表示这个锁当前没有线程拿到
    private static class Sync extends AbstractQueuedSynchronizer {

        //检查锁是否被占用
        // 状态为0 表示没有被占用，返回false
        // 1 表示被占用，返回true
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        protected boolean tryAcquire(int arg) {
            //CAS更新状态值为1
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        protected boolean tryRelease(int arg) {
            if (Thread.currentThread() != getExclusiveOwnerThread()) {
                throw new IllegalMonitorStateException();
            }
            if (getState() == 0) {
                throw new UnsupportedOperationException();
            }

            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        Condition newCondition() {
            return new ConditionObject();
        }
    }

    private final Sync sycn = new Sync();

    @Override
    public void lock() {
        sycn.acquire(1);

    }


    @Override
    public void unlock() {
        sycn.release(1);

    }

    @Override
    public Condition newCondition() {
        return sycn.newCondition();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sycn.acquireInterruptibly(1);

    }


    @Override
    public boolean tryLock() {
        return sycn.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sycn.tryAcquireNanos(1, unit.toNanos(time));
    }

}