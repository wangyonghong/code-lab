package com.nien.demo.lock;

import com.nien.demo.util.Print;

import java.util.concurrent.locks.Lock;

import static com.nien.demo.util.ThreadUtil.sleepMilliSeconds;

/**
 * Created by 尼恩@疯狂创客圈.
 */
public class IncrementData {
    public static int sum = 0;

    public static void lockAndFastIncrease(Lock lock) {
        //step1：抢占锁
        // Print.synTco(curThreadName()+" -- 本线程开始抢占锁");
        lock.lock();
        try {
            //Print.synTco(curThreadName()+" ^-^本线程抢到了锁");
            //step2：执行临界区代码
            sum++;
        } finally {
            //step3：释放锁
            lock.unlock();
            //Print.synTco("本线程释放了锁");
        }
    }

    public static void lockAndIncrease(Lock lock) {
        Print.synTco(" -- 开始抢占锁");
        lock.lock();
        try {
            Print.synTco(" ^-^ 抢到了锁");
            sum++;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //演示方法：可中断抢锁
    public static void lockInterruptiblyAndIncrease(Lock lock) {
        Print.synTco(" 开始抢占锁");
        try {
            lock.lockInterruptibly();
        } catch (InterruptedException e) {
            Print.synTco("抢占被中断，抢锁失败");
            // e.printStackTrace();
            return;
        }
        try {
            Print.synTco("抢到了锁，同步执行1秒");
            sleepMilliSeconds(1000);
            sum++;
            if (Thread.currentThread().isInterrupted()) {
                Print.synTco("同步执行被中断");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public static void tryLockAndIncrease(Lock lock) {
        if (lock.tryLock()) {
            Print.synTco("本线程抢到了锁");

            try {
                Thread.sleep(100);
                sum++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                Print.synTco("本线程释放了锁");

            }
        } else {
            // perform alternative actions
            Print.synTco("本线程抢锁失败");
        }
    }

}
