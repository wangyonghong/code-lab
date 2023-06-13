package com.nien.demo.lock;

import com.nien.demo.util.DateUtil;
import com.nien.demo.util.Print;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 尼恩@疯狂创客圈.
 */
public class SemaphoreTest {

    @org.junit.Test
    public void testShareLock() throws InterruptedException {
        // 排队总人数（请求总数）
        final int USER_TOTAL = 10;
        // 可同时受理业务的窗口数量（同时并发执行的线程数）
        final int PERMIT_TOTAL = 2;
        //线程池，用于多线程模拟测试
        final CountDownLatch countDownLatch = new CountDownLatch(USER_TOTAL);
        //创建信号量,含有2个许可
        final Semaphore semaphore = new Semaphore(PERMIT_TOTAL);
        AtomicInteger index = new AtomicInteger(0);
        //创建Runnable可执行实例
        Runnable r = () ->
        {
            try {
                Calendar calendar = new GregorianCalendar();//日历实例
                //抢占一个许可
                semaphore.acquire(1);

                //模拟业务操作： 处理排队业务
                Print.tco(DateUtil.getNowTime() + ", 受理处理中...,服务号: " + index.incrementAndGet());
                Thread.sleep(1000);
                //释放一个信号
                semaphore.release(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        };


        //创建4条线程
        Thread[] tArray = new Thread[USER_TOTAL];
        for (int i = 0; i < USER_TOTAL; i++) {
            tArray[i] = new Thread(r, "线程" + i);
        }
        //启动4条线程
        for (int i = 0; i < USER_TOTAL; i++) {
            tArray[i].start();
        }


        countDownLatch.await();
    }


}
