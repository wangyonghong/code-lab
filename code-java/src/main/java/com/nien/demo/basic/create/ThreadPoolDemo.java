package com.nien.demo.basic.create;

import com.nien.demo.basic.create3.CreateThreadPoolDemo;
import com.nien.demo.util.Print;
import com.nien.demo.util.ThreadUtil;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by 尼恩@疯狂创客圈.
 */

public class ThreadPoolDemo {
    public static final int MAX_TURN = 5;


    static int threadNo = 1;

    static class RunTarget implements Runnable  //① 实现Runnable接口
    {
        public void run()  //② 在这些写业务逻辑
        {
            for (int j = 1; j < MAX_TURN; j++) {
                Print.cfo(ThreadUtil.getCurThreadName() + ", 轮次：" + j);
            }

            Print.cfo(ThreadUtil.getCurThreadName() + " 运行结束.");
        }
    }

    @Test
    public void testIoIntenseTargetThreadPool() {
        ThreadPoolExecutor pool = ThreadUtil.getIoIntenseTargetThreadPool();
        for (int i = 0; i < 2; i++) {
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    for (int j = 1; j < MAX_TURN; j++) {
                        Print.cfo(ThreadUtil.getCurThreadName() + ", 轮次：" + j);
                    }
                    Print.cfo(ThreadUtil.getCurThreadName() + " 运行结束.");
                }
            });

        }
        ThreadUtil.sleepMilliSeconds(Integer.MAX_VALUE);
    }

    @Test
    public void testCpuIntenseTargetThreadPool() {
        ThreadPoolExecutor pool = ThreadUtil.getCpuIntenseTargetThreadPool();
        for (int i = 0; i < 2; i++) {
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    for (int j = 1; j < MAX_TURN; j++) {
                        Print.cfo(ThreadUtil.getCurThreadName() + ", 轮次：" + j);
                    }
                    Print.cfo(ThreadUtil.getCurThreadName() + " 运行结束.");
                }
            });

        }
        ThreadUtil.sleepMilliSeconds(Integer.MAX_VALUE);
    }

    @Test
    public void testMixedThreadPool() {
        System.getProperties().setProperty(ThreadUtil.MIXED_THREAD_AMOUNT, "80");
        // 获取自定义的混合线程池
        ExecutorService pool =
                ThreadUtil.getMixedTargetThreadPool();
        for (int i = 0; i < 1000; i++) {
            try {
                ThreadUtil.sleepMilliSeconds(10);
                pool.submit(new CreateThreadPoolDemo.TargetTask());

            } catch (RejectedExecutionException e) {
                e.printStackTrace();
            }
        }
        //等待10s
        ThreadUtil.sleepSeconds(10);
        Print.tco("关闭线程池");
    }

}