package com.nien.demo.lock;

import com.nien.demo.lock.custom.CLHLock;
import com.nien.demo.lock.custom.SimpleMockLock;
import com.nien.demo.util.Print;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.nien.demo.util.ThreadUtil.sleepMilliSeconds;

/**
 * Created by 尼恩@疯狂创客圈.
 */
public class LockTest {


    @org.junit.Test
    public void testReentrantLock() {
        // 每条线程的执行轮数
        final int TURNS = 1000;
        // 线程数
        final int THREADS = 10;

        //线程池，用于多线程模拟测试
        ExecutorService pool = Executors.newFixedThreadPool(THREADS);

        //可重入、独占锁对象
        Lock lock = new ReentrantLock();
        // 倒数闩
        CountDownLatch countDownLatch = new CountDownLatch(THREADS);
        long start = System.currentTimeMillis();

        //10条线程并发执行
        for (int i = 0; i < THREADS; i++) {
            pool.submit(() ->
            {
                try {
                    //累加 1000 次
                    for (int j = 0; j < TURNS; j++) {
                        //传入锁，执行一次累加
                        IncrementData.lockAndFastIncrease(lock);
                    }
                    Print.tco("本线程累加完成");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //线程执行完成，倒数闩减少一次
                countDownLatch.countDown();

            });
        }
        try {
            //等待倒数闩归零，所有线程结束
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        float time = (System.currentTimeMillis() - start) / 1000F;
        //输出统计结果
        Print.tcfo("运行的时长为：" + time);
        Print.tcfo("累加结果为：" + IncrementData.sum);
    }


    /**
     * 公平锁测试用例
     */
    @org.junit.Test
    public void testFairLock() throws InterruptedException {
        //创建可重入锁，创建为公平锁的类型
        Lock lock = new CLHLock();

        //创建Runnable可执行实例
        Runnable r = () -> IncrementData.lockAndIncrease(lock);

        //创建4条线程
        Thread[] tArray = new Thread[4];
        for (int i = 0; i < 4; i++) {
            tArray[i] = new Thread(r, "线程" + i);
        }
        //启动4条线程
        for (int i = 0; i < 4; i++) {
            tArray[i].start();
        }
        Thread.sleep(Integer.MAX_VALUE);
    }


    /**
     * 非公平锁测试用例
     */
    @org.junit.Test
    public void testNotFairLock() throws InterruptedException {
        //创建可重入锁，默认的非公平锁
        Lock lock = new ReentrantLock(false);

        //创建Runnable可执行实例
        Runnable r = () -> IncrementData.lockAndIncrease(lock);

        //创建4条线程
        Thread[] tArray = new Thread[4];
        for (int i = 0; i < 4; i++) {
            tArray[i] = new Thread(r, "线程" + i);
        }
        //启动4条线程
        for (int i = 0; i < 4; i++) {
            tArray[i].start();
        }
        Thread.sleep(Integer.MAX_VALUE);
    }

    //测试用例：抢锁过程可中断
    @org.junit.Test
    public void testInterruptLock() throws InterruptedException {
        //创建可重入锁，默认的非公平锁
        Lock lock = new ReentrantLock();

        //创建Runnable可执行实例
        Runnable r = () -> IncrementData.lockInterruptiblyAndIncrease(lock);
        Thread t1 = new Thread(r, "thread-1");  //创建第1条线程
        Thread t2 = new Thread(r, "thread-2");  //创建第2条线程


        t1.start(); //启动第1条线程
        t2.start(); //启动第2条线程
        sleepMilliSeconds(100);
        Print.synTco("等待100毫秒，中断两个线程");

        t1.interrupt(); //启动第2条线程
        t2.interrupt(); //启动第2条线程

        Thread.sleep(Integer.MAX_VALUE);
    }

    //获取ThreadMXBean
    public static ThreadMXBean mbean = ManagementFactory.getThreadMXBean();

    //测试用例：抢占两把锁，造成死锁，然后进行死锁监测和部分中断
    @org.junit.Test
    public void testDeadLock() throws InterruptedException {
        //创建可重入锁，默认的非公平锁
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();

        //Runnable可执行实例1: 先抢占lock1， 再抢占 lock2
        Runnable r1 = () -> TwoLockDemo.useTowlockInterruptiblyLock(lock1, lock2);

        //Runnable可执行实例2: 先抢占lock2， 再抢占 lock1
        Runnable r2 = () -> TwoLockDemo.useTowlockInterruptiblyLock(lock2, lock1);
        Thread t1 = new Thread(r1, "thread-1");  //创建第1条线程
        Thread t2 = new Thread(r2, "thread-2");  //创建第2条线程
        t1.start(); //启动第1条线程
        t2.start(); //启动第2条线程

        //等待一段时间再执行死锁检测
        Thread.sleep(2000);
        Print.tcfo("等待2秒，开始死锁监测和处理");

        //获取到所有死锁线程的id
        long[] deadlockedThreads = mbean.findDeadlockedThreads();
        if (deadlockedThreads.length > 0) {
            Print.tcfo("发生了死锁，输出死锁线程的信息");
            //遍历数组获取所有的死锁线程详细堆栈信息并打印
            for (long pid : deadlockedThreads) {
                //此方法获取不带有堆栈跟踪信息的线程数据
                //hreadInfo threadInfo = mbean.getThreadInfo(pid);
                //第二个参数指定转储多少项堆栈跟踪信息,设置为Integer.MAX_VALUE可以转储所有的堆栈跟踪信息
                ThreadInfo threadInfo = mbean.getThreadInfo(pid, Integer.MAX_VALUE);
                Print.tcfo(threadInfo);
            }
            Print.tcfo("中断一条线程，这里是线程：" + t1.getName());
            t1.interrupt();
        }
        Thread.sleep(Integer.MAX_VALUE);
    }

    //测试用例：抢占两把锁，通过限时等待的方式
    @org.junit.Test
    public void testTryTowLock() throws InterruptedException {
        //创建可重入锁，默认的非公平锁
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();

        //Runnable可执行实例1: 先限时抢占lock1， 再限时抢占 lock2
        Runnable r1 = () -> TwoLockDemo.tryTowLock(lock1, lock2);

        //Runnable可执行实例2: 先限时抢占lock2， 再限时抢占 lock1
        Runnable r2 = () -> TwoLockDemo.tryTowLock(lock2, lock1);
        Thread t1 = new Thread(r1, "thread-1");  //创建第1条线程
        Thread t2 = new Thread(r2, "thread-2");  //创建第2条线程
        t1.start(); //启动第1条线程
        t2.start(); //启动第2条线程

        Print.tcfo("主线程结束");

        Thread.sleep(Integer.MAX_VALUE);
    }

    @org.junit.Test
    public void testCLHLockCapability() {
        // 速度对比
        // ReentrantLock  1 000 000 次 0.154 秒
        // CLHLock        1 000 000 次 2.798 秒

        // 每条线程的执行轮数
        final int TURNS = 100000;

        // 线程数
        final int THREADS = 10;

        //线程池，用于多线程模拟测试
        ExecutorService pool = Executors.newFixedThreadPool(THREADS);

        Lock lock = new CLHLock();
//        Lock lock = new ReentrantLock();

        // 倒数闩
        CountDownLatch countDownLatch = new CountDownLatch(THREADS);
        long start = System.currentTimeMillis();
        for (int i = 0; i < THREADS; i++) {
            pool.submit(() ->
            {

                for (int j = 0; j < TURNS; j++) {
                    IncrementData.lockAndFastIncrease(lock);
                }
                Print.tcfo("本线程累加完成");
                //倒数闩减少1次
                countDownLatch.countDown();
            });
        }
        try {
            //等待倒数闩归0，所有线程结束
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        float time = (System.currentTimeMillis() - start) / 1000F;
        //输出统计结果
        Print.tcfo("运行的时长为：" + time);
        Print.tcfo("累加结果为：" + IncrementData.sum);
    }


    @org.junit.Test
    public void testMockLock() {
        // 每条线程的执行轮数
        final int TURNS = 1000;
        // 线程数
        final int THREADS = 10;

        //线程池，用于多线程模拟测试
        ExecutorService pool = Executors.newFixedThreadPool(THREADS);

        //可重入、独占锁对象
        Lock lock = new SimpleMockLock();
        // 倒数闩
        CountDownLatch countDownLatch = new CountDownLatch(THREADS);
        long start = System.currentTimeMillis();

        //10条线程并发执行
        for (int i = 0; i < THREADS; i++) {
            pool.submit(() ->
            {
                try {
                    //累加 1000 次
                    for (int j = 0; j < TURNS; j++) {
                        //传入锁，执行一次累加
                        IncrementData.lockAndFastIncrease(lock);
                    }
                    Print.tco("本线程累加完成");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //线程执行完成，倒数闩减少一次
                countDownLatch.countDown();

            });
        }
        try {
            //等待倒数闩归零，所有线程结束
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        float time = (System.currentTimeMillis() - start) / 1000F;
        //输出统计结果
        Print.tcfo("运行的时长为：" + time);
        Print.tcfo("累加结果为：" + IncrementData.sum);
    }

}