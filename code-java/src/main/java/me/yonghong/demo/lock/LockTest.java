package me.yonghong.demo.lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/**
 * @author yonghongwang#163.com
 * @link <a href="https://zhuanlan.zhihu.com/p/71156910">通俗易懂 悲观锁、乐观锁、可重入锁、自旋锁、偏向锁、轻量/重量级锁、读写锁、各种锁及其Java实现！</a>
 * @since 2021/8/3
 */
public class LockTest {

    public static void main(String[] args) {
        // 死锁
        // new LockTest().testNRSpinLock();

        // 不会死锁
        new LockTest().testRSpinLock();
    }

    public void testSynchronized() {
        // synchronized锁升级：偏向锁 → 轻量级锁 → 重量级锁
        // 那么自旋锁在哪里呢？这里的轻量级锁就是一种自旋锁。
        //
        // 初次执行到synchronized代码块的时候，锁对象变成偏向锁（通过CAS修改对象头里的锁标志位），
        // 字面意思是“偏向于第一个获得它的线程”的锁。执行完同步代码块后，线程并不会主动释放偏向锁。
        // 当第二次到达同步代码块时，线程会判断此时持有锁的线程是否就是自己（持有锁的线程ID也在对象头里），
        // 如果是则正常往下执行。由于之前没有释放锁，这里也就不需要重新加锁。
        // 如果自始至终使用锁的线程只有一个，很明显偏向锁几乎没有额外开销，性能极高。
        //
        // 一旦有第二个线程加入锁竞争，偏向锁就升级为轻量级锁（自旋锁）。
        // 这里要明确一下什么是锁竞争：如果多个线程轮流获取一个锁，但是每次获取锁的时候都很顺利，没有发生阻塞，
        // 那么就不存在锁竞争。只有当某线程尝试获取锁的时候，发现该锁已经被占用，只能等待其释放，这才发生了锁竞争。
        //
        // 偏向锁的一个特性是，持有锁的线程在执行完同步代码块时不会释放锁。
        // 那么当第二个线程执行到这个synchronized代码块时是否一定会发生锁竞争然后升级为轻量级锁呢？
        // 线程A第一次执行完同步代码块后，当线程B尝试获取锁的时候，发现是偏向锁，会判断线程A是否仍然存活。
        // 如果线程A仍然存活，将线程A暂停，此时偏向锁升级为轻量级锁，之后线程A继续执行，线程B自旋。
        // 但是如果判断结果是线程A不存在了，则线程B持有此偏向锁，锁不升级。
        //
        // 在轻量级锁状态下继续锁竞争，没有抢到锁的线程将自旋，即不停地循环判断锁是否能够被成功获取。
        // 获取锁的操作，其实就是通过CAS修改对象头里的锁标志位。先比较当前锁标志位是否为“释放”，
        // 如果是则将其设置为“锁定”，比较并设置是原子性发生的。这就算抢到锁了，然后线程将当前锁的持有者信息修改为自己。
        //
        // 长时间的自旋操作是非常消耗资源的，一个线程持有锁，其他线程就只能在原地空耗CPU，执行不了任何有效的任务，
        // 这种现象叫做忙等（busy-waiting）。
        // 如果多个线程用一个锁，但是没有发生锁竞争，或者发生了很轻微的锁竞争，
        // 那么synchronized就用轻量级锁，允许短时间的忙等现象。
        // 这是一种折衷的想法，短时间的忙等，换取线程在用户态和内核态之间切换的开销。
        //
        // 显然，此忙等是有限度的（有个计数器记录自旋次数，默认允许循环10次，可以通过虚拟机参数更改）。
        // 如果锁竞争情况严重，某个达到最大自旋次数的线程，会将轻量级锁升级为重量级锁（依然是CAS修改锁标志位，但不修改持有锁的线程ID）。
        // 当后续线程尝试获取锁时，发现被占用的锁是重量级锁，则直接将自己挂起（而不是忙等），等待将来被唤醒。
        // 在JDK1.6之前，synchronized直接加重量级锁，很明显现在得到了很好的优化。
        //
        // 一个锁只能按照 偏向锁、轻量级锁、重量级锁的顺序逐渐升级（也有叫锁膨胀的），不允许降级。
        synchronized (this) {
            // do something
        }
    }

    public void testReentrantLock() {
        // ReentrantLock、ReadLock、WriteLock 是 Lock 接口最重要的三个实现类。
        // 对应了“可重入锁”、“读锁”、“写锁”

        // 可重入锁的字面意思是“可以重新进入的锁”，即允许同一个线程多次获取同一把锁。
        // 比如一个递归函数里有加锁操作，递归过程中这个锁会阻塞自己吗？
        // 如果不会，那么这个锁就是可重入锁（因为这个原因可重入锁也叫做递归锁）。

        // Java里只要以 Reentrant 开头命名的锁都是可重入锁，
        // 而且JDK提供的所有现成的Lock实现类，包括 synchronized 关键字锁都是可重入的。
        //
        // StampedLock是不可重入锁。

        ReentrantLock reentrantLock = new ReentrantLock();

        // 如果多个线程申请一把公平锁，那么当锁释放的时候，先申请的先得到，非常公平。
        // 显然如果是非公平锁，后申请的线程可能先获取到锁，是随机或者按照其他优先级排序的。

        // 对 ReentrantLock 类而言，通过构造函数传参可以指定该锁是否是公平锁，默认是非公平锁。
        // 一般情况下，非公平锁的吞吐量比公平锁大，如果没有特殊要求，优先使用非公平锁。

        // 对于 synchronized 而言，它也是一种非公平锁，但是并没有任何办法使其变成公平锁。

        // 可中断锁，字面意思是“可以响应中断的锁”。
        // synchronized 就是不可中断锁，而 Lock 的实现类都是可中断锁。
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(false);
        ReadLock readLock = reentrantReadWriteLock.readLock();
        WriteLock writeLock = reentrantReadWriteLock.writeLock();
    }

    /**
     * 悲观锁、乐观锁
     */
    public void pessimisticOrOptimisticLockTest() {
        // 在Java里使用的各种锁，几乎全都是悲观锁。
        // synchronized 从偏向锁、轻量级锁到重量级锁，全是悲观锁。
        // JDK 提供的 Lock 实现类全是悲观锁。
        // 其实只要有“锁对象”出现，那么就一定是悲观锁。
        // 因为乐观锁不是锁，而是一个在循环里尝试CAS的算法。
        //
        // 那JDK并发包里到底有没有乐观锁呢？
        // 有。java.util.concurrent.atomic 包里面的原子类都是利用乐观锁实现的。
        // StampedLock 提供了乐观读锁，可取代 ReadWriteLock 以进一步提升并发性能。
        AtomicInteger atomicInteger = new AtomicInteger(0);
        atomicInteger.getAndIncrement();
    }

    public void testCondition() {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
    }

    public void testSemaphore() {
        Semaphore semaphore = new Semaphore(0);
    }

    public void testLockSupport() {
        LockSupport.park();
        LockSupport.unpark(Thread.currentThread());
    }

    public void testAbstractQueuedSynchronizer() {
        AbstractQueuedSynchronizer abstractQueuedSynchronizer = new AbstractQueuedSynchronizer() {
        };
    }

    public void testNRSpinLock() {
        ILock lock = new NRSpinLock();
        methodA(lock);
    }

    public void testRSpinLock() {
        ILock lock = new RSpinLock();
        methodA(lock);
    }

    private void methodA(ILock lock) {
        lock.lock();
        System.out.println("methodA");
        methodB(lock);
        lock.unlock();
    }

    private void methodB(ILock lock) {
        lock.lock();
        System.out.println("methodB");
        lock.unlock();
    }

    interface ILock {
        /**
         * 加锁
         */
        void lock();

        /**
         * 解锁
         */
        void unlock();
    }

    /**
     * 不可重入锁
     */
    static class NRSpinLock implements ILock {

        private AtomicReference<Thread> currLock = new AtomicReference<>();

        @Override
        public void lock() {
            Thread currentThread = Thread.currentThread();
            while (!currLock.compareAndSet(null, currentThread)) {
                ;
            }
        }

        @Override
        public void unlock() {
            Thread currentThread = Thread.currentThread();
            currLock.compareAndSet(currentThread, null);
        }
    }

    /**
     * 可重入锁
     */
    static class RSpinLock implements ILock {

        private AtomicReference<Thread> currLock = new AtomicReference<>();
        private int count = 0;

        @Override
        public void lock() {
            Thread currentThread = Thread.currentThread();
            if (currentThread == currLock.get()) {
                count++;
                return;
            }
            while (!currLock.compareAndSet(null, currentThread)) {
                ;
            }
        }

        @Override
        public void unlock() {
            Thread currentThread = Thread.currentThread();
            if (currentThread == currLock.get()) {
                if (count != 0) {
                    count--;
                } else {
                    currLock.compareAndSet(currentThread, null);
                }
            }
        }
    }
}
