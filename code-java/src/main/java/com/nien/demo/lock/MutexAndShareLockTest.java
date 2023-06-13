package com.nien.demo.lock;

import com.nien.demo.lock.custom.MutexLock;
import com.nien.demo.lock.custom.ShareLock;
import com.nien.demo.util.Print;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;

/**
 * Created by 尼恩@疯狂创客圈.
 */
public class MutexAndShareLockTest {


    @org.junit.Test
    public void testMutexLock() {
        // 每条线程的执行轮数
        final int TURNS = 1000;
        // 线程数
        final int THREADS = 10;

        //线程池，用于多线程模拟测试
        ExecutorService pool = Executors.newFixedThreadPool(THREADS);

        Lock lock = new MutexLock();

        // 倒数闩
        CountDownLatch countDownLatch = new CountDownLatch(THREADS);
        long start = System.currentTimeMillis();
        for (int i = 0; i < THREADS; i++) {
            pool.submit(() ->
            {
                try {
                    for (int j = 0; j < TURNS; j++) {
                        IncrementData.lockAndFastIncrease(lock);
                    }
                    Print.tcfo("本线程累加完成");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //等待所有线程结束
                countDownLatch.countDown();

            });
        }
        try {
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
    public void testShareLock() {
        // 每条线程的执行轮数
        final int TURNS = 1000;
        // 线程数
        final int THREADS = 10;

        //线程池，用于多线程模拟测试
        ExecutorService threadPool = Executors.newFixedThreadPool(THREADS);

        Lock shareLock = new ShareLock(10);

        // 倒数闩
        CountDownLatch countDownLatch = new CountDownLatch(THREADS);
        long start = System.currentTimeMillis();
        for (int i = 0; i < THREADS; i++) {
            threadPool.submit(() ->
            {
                try {
                    for (int j = 0; j < TURNS; j++) {
                        //抢占共享锁
                        shareLock.lock();
                        //模拟数据操作
                        Print.tcfo("数据操作");
                        //释放共享锁
                        shareLock.unlock();
                    }
                    Print.tcfo("本线程累加完成");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //等待所有线程结束
                countDownLatch.countDown();

            });
        }
        try {
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