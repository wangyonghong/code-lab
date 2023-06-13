package com.nien.demo.lock;


import com.nien.demo.util.Print;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.nien.demo.util.ThreadUtil.sleepSeconds;


public class ReentrantCommunicationTest {
    // 创建一个显示锁
    static Lock lock = new ReentrantLock();
    //获取一个显示锁绑定的Condition对象
    static private Condition condition = lock.newCondition();

    //等待线程
    static class WaitTarget implements Runnable {
        public void run() {
            lock.lock(); // 抢锁
            try {
                Print.tcfo("我是等待方");
                condition.await(); //开始等待,并且释放锁
                Print.tco("收到通知，等待方继续执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();//释放锁
            }
        }
    }

    //通知线程
    static class NotifyTarget implements Runnable {
        public void run() {
            lock.lock(); // 抢锁
            try {
                Print.tcfo("我是通知方");
                condition.signal();//发送通知
                Print.tco("发出通知了，但是线程还没有立马释放锁");
            } finally {
                lock.unlock(); //释放锁
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //创建等待线程
        Thread waitThread = new Thread(new WaitTarget(), "WaitThread");
        //启动等待线程
        waitThread.start();
        sleepSeconds(1);
        //创建通知线程
        Thread notifyThread = new Thread(new NotifyTarget(), "NotifyThread");
        //启动通知线程
        notifyThread.start();
    }
}

