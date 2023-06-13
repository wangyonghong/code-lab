package com.nien.demo.asq;


import com.nien.demo.util.Print;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.MAX_VALUE;

public class Test {
    private static SelfLock lock = new SelfLock();

    private static int sum = 0;

    // 线程数
    static final int THREADS = 10;
    // 每条线程的执行轮数
    static final int TURNS = 100000000;
    //线程池，用于多线程模拟测试
    private ExecutorService pool = Executors.newFixedThreadPool(THREADS);

    @org.junit.Test
    public void testLimit() {
        // 倒数闩
        CountDownLatch countDownLatch = new CountDownLatch(THREADS);
        long start = System.currentTimeMillis();
        for (int i = 0; i < THREADS; i++) {
            pool.submit(() ->
            {
                try {
                    for (int j = 0; j < TURNS; j++) {
//                        increase();
                        increaseTypically();
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
        Print.tcfo("累加结果为：" + sum);
    }

    public void increase() {
        lock.lock();
        sum++;
        lock.unlock();
    }

    public void increaseTypically() {
        SelfLock lock = Test.lock;
        if (lock.tryLock()) {
            try {
                sum++;
            } finally {
                lock.unlock();
            }
        } else {
            // perform alternative actions
            Print.tcfo("本线程抢锁失败");
        }
    }


    private void lockTest() {
        long currentTime = System.currentTimeMillis();
        try {
            lock.lock();
            Print.tcfo("enter lockTest");
            while (System.currentTimeMillis() - currentTime <= 5000) {
                //assume do something
            }

        } finally {
            Print.tcfo("leave lockTest");
            lock.unlock();
        }
    }

    private void tryLockTest() {

        long currentTime = System.currentTimeMillis();


        if (lock.tryLock()) {
            Print.tcfo("enter tryLockTest");

            try {
                while (System.currentTimeMillis() - currentTime <= 100) {
                    //assume do something
                }

            } finally {
                Print.tcfo("release the lock， then leave tryLockTest");
                lock.unlock();
            }

        } else {
            Print.tcfo(" tryLockTest  CAN NOT get the lock");

        }
    }


    private void tryLockInterruptTest() {

        long currentTime = System.currentTimeMillis();


        try {
            Print.tcfo("Begin time: " + System.currentTimeMillis());
            if (lock.tryLock(1, TimeUnit.SECONDS)) {

                Print.tcfo("enter tryLockInterruptTest");

                try {
                    while (System.currentTimeMillis() - currentTime <= 100) {
                        //assume do something
                    }

                } finally {
                    Print.tcfo("release the lock， then leave tryLockInterruptTest");
                    lock.unlock();
                }

            } else {
                Print.tcfo(" tryLockTest  CAN NOT get the lock");
            }
            Print.tcfo("take time: " + (System.currentTimeMillis() - currentTime));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @org.junit.Test
    public void testTryLock() throws InterruptedException {

        pool.submit(() -> lockTest());
        pool.submit(() -> tryLockTest());
        Thread.sleep(MAX_VALUE);

    }

    @org.junit.Test
    public void testLockInterrupt() throws InterruptedException {

        pool.submit(() -> tryLockInterruptTest());
        pool.submit(() -> tryLockTest());

        Thread.sleep(MAX_VALUE);

    }


}