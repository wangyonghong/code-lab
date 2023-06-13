package com.nien.demo.lock;


import com.nien.demo.util.Print;

import java.util.concurrent.locks.LockSupport;

import static com.nien.demo.util.ThreadUtil.sleepSeconds;

/**
 * Created by 尼恩@疯狂创客圈.
 */
public class LockSupportDemo {
    public static class ChangeObjectThread extends Thread {
        public ChangeObjectThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            Print.tco("即将进入无限时阻塞");
            //阻塞当前线程
            LockSupport.park();
            if (Thread.currentThread().isInterrupted()) {
                Print.tco("被中断了，但任然会继续执行");
            } else {
                Print.tco("被重新唤醒了");
            }
        }
    }


    @org.junit.Test
    public void testLockSupport() {
        ChangeObjectThread t1 = new ChangeObjectThread("线程一");
        ChangeObjectThread t2 = new ChangeObjectThread("线程二");
        //启动线程一
        t1.start();
        sleepSeconds(1);
        //启动线程二
        t2.start();
        sleepSeconds(1);
        //中断线程一
        t1.interrupt();
        //唤醒线程二
        LockSupport.unpark(t2);
    }

    @org.junit.Test
    public void testLockSupport2() {
        Thread t1 = new Thread(() ->
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Print.tco("即将进入无限时阻塞");
            //阻塞当前线程
            LockSupport.park();
            Print.tco("被重新唤醒了");

        }, "演示线程");
        t1.start();
        //唤醒一次没有使用 LockSupport.park()阻塞的线程
        LockSupport.unpark(t1);
        //再唤醒一次没有使用 LockSupport.park()阻塞的线程
        LockSupport.unpark(t1);
        sleepSeconds(2);
        //中断线程一
        //第三唤醒使用 LockSupport.park()阻塞的线程
        LockSupport.unpark(t1);

    }
}

