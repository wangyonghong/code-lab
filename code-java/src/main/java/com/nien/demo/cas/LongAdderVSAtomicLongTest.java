package com.nien.demo.cas;
//...省略import

import com.nien.demo.util.Print;
import com.nien.demo.util.ThreadUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class LongAdderVSAtomicLongTest {
    // 每条线程的执行轮数
    final int TURNS = 100000000;

    @org.junit.Test
    public void testAtomicLong() {
        // 并发任务数
        final int TASK_AMOUNT = 10;

        //线程池，获取CPU密集型任务线程池
        ExecutorService pool = ThreadUtil.getCpuIntenseTargetThreadPool();

        //定义一个原子对象
        AtomicLong atomicLong = new AtomicLong(0);

        // 线程同步倒数闩
        CountDownLatch countDownLatch = new CountDownLatch(TASK_AMOUNT);
        long start = System.currentTimeMillis();
        for (int i = 0; i < TASK_AMOUNT; i++) {
            pool.submit(() ->
            {
                try {
                    for (int j = 0; j < TURNS; j++) {
                        atomicLong.incrementAndGet();
                    }
                    // Print.tcfo("本线程累加完成");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //倒数闩，倒数一次
                countDownLatch.countDown();

            });
        }

        try {
            //等待倒数闩完成所有的倒数操作
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        float time = (System.currentTimeMillis() - start) / 1000F;
        //输出统计结果
        Print.tcfo("运行的时长为：" + time);
        Print.tcfo("累加结果为：" + atomicLong.get());
    }

    @org.junit.Test
    public void testLongAdder() {
        // 并发任务数
        final int TASK_AMOUNT = 10;

        //线程池，获取CPU密集型任务线程池
        ExecutorService pool = ThreadUtil.getCpuIntenseTargetThreadPool();

        //定义一个LongAdder 对象
        LongAdder longAdder = new LongAdder();
        // 线程同步倒数闩
        CountDownLatch countDownLatch = new CountDownLatch(TASK_AMOUNT);
        long start = System.currentTimeMillis();
        for (int i = 0; i < TASK_AMOUNT; i++) {
            pool.submit(() ->
            {
                try {
                    for (int j = 0; j < TURNS; j++) {
                        longAdder.add(1);
                    }
                    // Print.tcfo("本线程累加完成");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //倒数闩，倒数一次
                countDownLatch.countDown();

            });
        }

        try {
            //等待倒数闩完成所有的倒数操作
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        float time = (System.currentTimeMillis() - start) / 1000F;
        //输出统计结果
        Print.tcfo("运行的时长为：" + time);
        Print.tcfo("累加结果为：" + longAdder.longValue());
    }
}

