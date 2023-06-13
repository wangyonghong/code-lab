package com.nien.demo.lock.custom;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class SimpleMockLock implements Lock {
    //自定义的内部类实例：同步器
    private final Sync sync = new Sync();

    //自定义的内部类：同步器
    //AbstractQueuedSynchronizer.state 表示锁的状态
    // AbstractQueuedSynchronizer.state=1 表示 锁没有被占用
    // AbstractQueuedSynchronizer.state=0 表示 锁没已经被占用
    private static class Sync extends AbstractQueuedSynchronizer {

        //钩子方法
        protected boolean tryAcquire(int arg) {
            //CAS更新状态值为1
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        //钩子方法
        protected boolean tryRelease(int arg) {
            //如果当前线程不是占用锁的线程
            if (Thread.currentThread() != getExclusiveOwnerThread()) {
                //抛出非法状态的异常
                throw new IllegalMonitorStateException();
            }
            //如果锁的状态为没有占用
            if (getState() == 0) {
                //抛出非法状态的异常
                throw new IllegalMonitorStateException();
            }
            //接下来不需要使用CAS操作，以为下面的操作不存在并发场景
            setExclusiveOwnerThread(null);
            //设置状态
            setState(0);
            return true;
        }
    }

    //抢锁：将节点添加到等待队列的尾部
    @Override
    public void lock() {
        //开启同步器的抢锁流程，将节点添加到等待队列的尾部
        sync.acquire(1);
    }

    //释放锁
    @Override
    public void unlock() {
        //修改队列中当前线程的节点状态为0，启动后继节点的线程执行
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        throw new IllegalStateException(
                "方法 'newCondition' 尚未实现!");
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        throw new IllegalStateException(
                "方法 'lockInterruptibly' 尚未实现!");

    }


    @Override
    public boolean tryLock() {
        throw new IllegalStateException(
                "方法 'tryLock' 尚未实现!");
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        throw new IllegalStateException(
                "方法 'tryLock' 尚未实现!");
    }

}