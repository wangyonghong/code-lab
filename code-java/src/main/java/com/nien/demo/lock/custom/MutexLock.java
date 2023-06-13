package com.nien.demo.lock.custom;

import com.nien.demo.util.Print;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

public class MutexLock implements Lock {


    private volatile Thread owner;

    private final AtomicInteger state = new AtomicInteger(0);

    private final ConcurrentLinkedQueue<Thread> waitersQueue =
            new ConcurrentLinkedQueue<Thread>();


    public void lock() {
        Thread currentThread = Thread.currentThread();
        if (owner != null && (owner == Thread.currentThread())) {
            return;
        }
        try {
            waitersQueue.add(currentThread);
            while (true) {
                boolean succeed = state.compareAndSet(0, 1);
                if (succeed) {
                    owner = currentThread;
                    break;
                }
                LockSupport.park();
            }
        } finally {
            waitersQueue.remove(currentThread);
        }

    }

    public void unlock() {
        if (owner != null && (owner != Thread.currentThread())) {
            Print.tcfo(" Wrong state, this thread don't own this lock. owner:" + owner);
        }
        while (true) {
            if (state.compareAndSet(1, 0)) {
                break;
            }
        }
        owner = null;
        if (!waitersQueue.isEmpty()) {

            for (Thread thread : waitersQueue) {
                LockSupport.unpark(thread);
            }
        }
    }

    /**
     * 仅供参考：可中断限时抢锁
     *
     * @param time 限时长度
     * @param unit 时间单位
     * @return 是否成功
     * @throws InterruptedException 中断异常
     */
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        long maxWaitInMills = -1L;
        if (time > 0) {
            maxWaitInMills = TimeUnit.MILLISECONDS.convert(time, unit);
        }
        Thread currentThread = Thread.currentThread();
        try {
            waitersQueue.add(currentThread);
            if (maxWaitInMills > 0) {
                boolean acquired = false;
                long left = maxWaitInMills * 1000L * 1000L;
                long cost = 0;
                while (true) {
                    //
                    if (state.compareAndSet(0, 1)) {
                        owner = currentThread;
                        acquired = true;
                        break;
                    }

                    left = left - cost;
                    long mark = System.nanoTime();
                    if (left <= 0) {
                        break;
                    }
                    LockSupport.parkNanos(left);
                    cost = mark - System.nanoTime();
                }
                return acquired;
            } else {
                while (true) {
                    if (state.compareAndSet(0, 1)) {
                        owner = currentThread;
                        break;
                    }
                    LockSupport.park();
                }
                return true;
            }
        } finally {
            waitersQueue.remove(currentThread);
        }

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
        throw new IllegalStateException("方法 'lockInterruptibly' 尚未实现!");
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
        throw new IllegalStateException("方法 'newCondition' 尚未实现!");
    }
}