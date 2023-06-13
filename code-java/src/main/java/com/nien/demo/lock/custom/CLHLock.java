package com.nien.demo.lock.custom;

import lombok.Data;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class CLHLock implements Lock {

    /**
     * 指向当前节点
     */
    private static ThreadLocal<Node> curNodeLocal = new ThreadLocal();
    private String name;
    /**
     * CLHLock队列的尾部
     */
    private AtomicReference<Node> tail = new AtomicReference<>(null);

    public CLHLock() {
        //设置尾部节点
        tail.getAndSet(Node.EMPTY);
    }

    public CLHLock(String name) {
        this.name = name;
        //设置尾部节点
        tail.getAndSet(Node.EMPTY);
    }

    //加锁：将节点添加到等待队列的尾部
    @Override
    public void lock() {
        Node curNode = new Node(true, null);
        Node preNode = tail.get();
        //CAS自旋：将当前节点插入到队列的尾部
        while (!tail.compareAndSet(preNode, curNode)) {
            preNode = tail.get();
        }
        //设置前驱
        curNode.setPrevNode(preNode);

        //监听前驱节点的locked变量，直到其值为false
        // 若前继节点的locked状态为true，则表示前一线程还在抢占或者占有锁
        while (curNode.getPrevNode().isLocked()) {
            //让出CPU时间片，提高性能
            Thread.yield();
        }
        // 能执行到这里，说明当前线程获取到了锁
        //  Print.tcfo("获取到了锁！！！");

        //设置在线程本地变量中，用于释放锁
        curNodeLocal.set(curNode);
    }

    @Override
    public void unlock() {
        Node curNode = curNodeLocal.get();
        curNode.setPrevNode(null);//help for GC
        curNodeLocal.set(null);
        curNode.setLocked(false);
    }

    @Data
    static class Node {
        public Node(boolean locked, Node prevNode) {
            this.locked = locked;
            this.prevNode = prevNode;
        }

        //true：当前线程正在抢占锁、或者已经占有锁
        // false：当前线程已经释放锁，下一个线程可以占有锁了
        volatile boolean locked;
        //前一个节点，需要监听其locked字段
        Node prevNode;


        //空节点
        public static final Node EMPTY =
                new Node(false, null);
    }


    /**
     * Acquires the lock unless the current thread is
     * {@linkplain Thread#interrupt interrupted}.
     *
     * <p>Acquires the lock if it is available and returns immediately.
     *
     * <p>If the lock is not available then the current thread becomes
     * disabled for thread scheduling purposes and lies dormant until
     * one of two things happens:
     *
     * <ul>
     * <li>The lock is acquired by the current thread; or
     * <li>Some other thread {@linkplain Thread#interrupt interrupts} the
     * current thread, and interruption of lock acquisition is supported.
     * </ul>
     *
     * <p>If the current thread:
     * <ul>
     * <li>has its interrupted status set on entry to this method; or
     * <li>is {@linkplain Thread#interrupt interrupted} while acquiring the
     * lock, and interruption of lock acquisition is supported,
     * </ul>
     * then {@link InterruptedException} is thrown and the current thread's
     * interrupted status is cleared.
     *
     * <p><b>Implementation Considerations</b>
     *
     * <p>The ability to interrupt a lock acquisition in some
     * implementations may not be possible, and if possible may be an
     * expensive operation.  The programmer should be aware that this
     * may be the case. An implementation should document when this is
     * the case.
     *
     * <p>An implementation can favor responding to an interrupt over
     * normal method return.
     *
     * <p>A {@code Lock} implementation may be able to detect
     * erroneous use of the lock, such as an invocation that would
     * cause deadlock, and may throw an (unchecked) exception in such
     * circumstances.  The circumstances and the exception type must
     * be documented by that {@code Lock} implementation.
     *
     * @throws InterruptedException if the current thread is
     *                              interrupted while acquiring the lock (and interruption
     *                              of lock acquisition is supported)
     */
    @Override
    public void lockInterruptibly() throws InterruptedException {
        throw new IllegalStateException(
                "方法 'lockInterruptibly' 尚未实现!");
    }

    /**
     * Acquires the lock only if it is free at the time of invocation.
     *
     * <p>Acquires the lock if it is available and returns immediately
     * with the value {@code true}.
     * If the lock is not available then this method will return
     * immediately with the value {@code false}.
     *
     * <p>A typical usage idiom for this method would be:
     * <pre> {@code
     * Lock lock = ...;
     * if (lock.tryLock()) {
     *   try {
     *     // manipulate protected state
     *   } finally {
     *     lock.unlock();
     *   }
     * } else {
     *   // perform alternative actions
     * }}</pre>
     * <p>
     * This usage ensures that the lock is unlocked if it was acquired, and
     * doesn't try to unlock if the lock was not acquired.
     *
     * @return {@code true} if the lock was acquired and
     * {@code false} otherwise
     */
    @Override
    public boolean tryLock() {
        throw new IllegalStateException(
                "方法 'tryLock' 尚未实现!");

    }

    /**
     * Acquires the lock if it is free within the given waiting time and the
     * current thread has not been {@linkplain Thread#interrupt interrupted}.
     *
     * <p>If the lock is available this method returns immediately
     * with the value {@code true}.
     * If the lock is not available then
     * the current thread becomes disabled for thread scheduling
     * purposes and lies dormant until one of three things happens:
     * <ul>
     * <li>The lock is acquired by the current thread; or
     * <li>Some other thread {@linkplain Thread#interrupt interrupts} the
     * current thread, and interruption of lock acquisition is supported; or
     * <li>The specified waiting time elapses
     * </ul>
     *
     * <p>If the lock is acquired then the value {@code true} is returned.
     *
     * <p>If the current thread:
     * <ul>
     * <li>has its interrupted status set on entry to this method; or
     * <li>is {@linkplain Thread#interrupt interrupted} while acquiring
     * the lock, and interruption of lock acquisition is supported,
     * </ul>
     * then {@link InterruptedException} is thrown and the current thread's
     * interrupted status is cleared.
     *
     * <p>If the specified waiting time elapses then the value {@code false}
     * is returned.
     * If the time is
     * less than or equal to zero, the method will not wait at all.
     *
     * <p><b>Implementation Considerations</b>
     *
     * <p>The ability to interrupt a lock acquisition in some implementations
     * may not be possible, and if possible may
     * be an expensive operation.
     * The programmer should be aware that this may be the case. An
     * implementation should document when this is the case.
     *
     * <p>An implementation can favor responding to an interrupt over normal
     * method return, or reporting a timeout.
     *
     * <p>A {@code Lock} implementation may be able to detect
     * erroneous use of the lock, such as an invocation that would cause
     * deadlock, and may throw an (unchecked) exception in such circumstances.
     * The circumstances and the exception type must be documented by that
     * {@code Lock} implementation.
     *
     * @param time the maximum time to wait for the lock
     * @param unit the time unit of the {@code time} argument
     * @return {@code true} if the lock was acquired and {@code false}
     * if the waiting time elapsed before the lock was acquired
     * @throws InterruptedException if the current thread is interrupted
     *                              while acquiring the lock (and interruption of lock
     *                              acquisition is supported)
     */
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        throw new IllegalStateException(
                "方法 'tryLock' 尚未实现!");
    }


    /**
     * Returns a new {@link Condition} instance that is bound to this
     * {@code Lock} instance.
     *
     * <p>Before waiting on the condition the lock must be held by the
     * current thread.
     * A call to {@link Condition#await()} will atomically release the lock
     * before waiting and re-acquire the lock before the wait returns.
     *
     * <p><b>Implementation Considerations</b>
     *
     * <p>The exact operation of the {@link Condition} instance depends on
     * the {@code Lock} implementation and must be documented by that
     * implementation.
     *
     * @return A new {@link Condition} instance for this {@code Lock} instance
     * @throws UnsupportedOperationException if this {@code Lock}
     *                                       implementation does not support conditions
     */
    @Override
    public Condition newCondition() {
        throw new IllegalStateException(
                "方法 'newCondition' 尚未实现!");
    }

    @Override
    public String toString() {
        return "CLHLock{" + name + '}';
    }
}