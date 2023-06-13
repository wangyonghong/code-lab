package com.nien.demo.lock;

import com.nien.demo.util.Print;
import com.nien.demo.util.RandomUtil;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

import static com.nien.demo.util.ThreadUtil.sleepMilliSeconds;

/**
 * Created by 尼恩@疯狂创客圈.
 */
public class TwoLockDemo {
    //演示代码：使用两把锁, 通过可以中断的方式抢锁
    public static void useTowlockInterruptiblyLock(Lock lock1, Lock lock2) {
        String lock1Name = lock1.toString().replace("java.util.concurrent.locks.", "");

        String lock2Name = lock2.toString().replace("java.util.concurrent.locks.", "");
        Print.synTco(" 开始抢第一把锁, 为：" + lock1Name);
        try {
            lock1.lockInterruptibly();
        } catch (InterruptedException e) {
            Print.synTco(" 被中断，抢第一把锁失败, 为：" + lock1Name);
            //e.printStackTrace();
            return;
        }

        try {
            Print.synTco(" 抢到了第一把锁, 为：" + lock1Name);
            Print.synTco(" 开始抢第二把锁, 为：" + lock2Name);
            try {
                lock2.lockInterruptibly();
            } catch (InterruptedException e) {
                Print.synTco(" 被中断，抢第二把锁失败,为：" + lock2Name);
                //e.printStackTrace();
                return;
            }
            try {
                Print.synTco(" 抢到了第二把锁：" + lock2Name);
                Print.synTco("do something ");
                //等待1000ms
                sleepMilliSeconds(1000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock2.unlock();
                Print.synTco(" 释放了第二把锁, 为：" + lock2Name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock1.unlock();
            Print.synTco(" 释放了第一把锁, 锁为：" + lock1Name);
        }
    }

    //演示代码：使用两把锁, 通过不可中断的方式抢锁
    public static void useTowLock(Lock lock1, Lock lock2) {
        String lock1Name = lock1.toString()
                .replace("java.util.concurrent.locks.", "");

        String lock2Name = lock2.toString()
                .replace("java.util.concurrent.locks.", "");
        Print.synTco(" -- 开始抢占锁, 锁为：" + lock1Name);

        lock1.lock();

        Print.synTco(" ^-^抢到了, 锁为：" + lock1Name);

        try {
            Print.synTco(" -- 开始抢占, 锁为：" + lock2Name);

            lock2.lock();

            Print.synTco(" ^-^抢到了, 锁为：" + lock2Name);
            try {
                Print.synTco("do something ");
                //等待1000ms
                Thread.sleep(10000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock2.unlock();
                Print.synTco(" 释放了, 锁为：" + lock2Name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock1.unlock();
            Print.synTco(" 释放了, 锁为：" + lock1Name);
        }
    }

    //演示代码：使用两把锁, 通过限时等待的方式抢锁
    public static void tryTowLock(Lock lock1, Lock lock2) {
        String lock1Name = lock1.toString()
                .replace("java.util.concurrent.locks.", "");

        String lock2Name = lock2.toString()
                .replace("java.util.concurrent.locks.", "");
        Print.synTco(" -- 开始抢占外部锁, 锁为：" + lock1Name);

        boolean lock1Succeed = false;
        boolean lock2Succeed = false;
        try {
            //等待一个10s秒的随机数
            int lock1Wait = RandomUtil.randInMod(10);
            lock1Succeed = lock1.tryLock(lock1Wait, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (lock1Succeed) {
            Print.synTco(" ^-^抢到了外部锁, 锁为：" + lock1Name);
        } else {
            Print.synTco(" @-@超时中断，抢占外部锁失败, 锁为：" + lock1Name);
            return;
        }
        try {
            Print.synTco(" -- 开始抢占内部锁, 锁为：" + lock2Name);
            try {
                //等待一个10s秒的随机数
                int lock2Wait = RandomUtil.randInMod(10);
                lock2Succeed = lock2.tryLock(lock2Wait, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (lock2Succeed) {
                Print.synTco(" ^-^抢到了内部锁, 锁为：" + lock2Name);
            } else {
                Print.synTco(" @-@超时中断，抢占内部锁失败, 锁为：" + lock2Name);
                return;
            }
            try {
                Print.synTco(" do something ");
                //等待1000ms
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock2.unlock();
                Print.synTco(" 释放了内部锁, 锁为：" + lock2Name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock1.unlock();
            Print.synTco(" 释放了外部锁, 锁为：" + lock1Name);
        }
    }

}
