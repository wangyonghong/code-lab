package com.nien.demo.designmodel.forkjoin;

import com.nien.demo.util.Print;
import com.nien.demo.util.ThreadUtil;
import org.junit.Assert;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by 尼恩@疯狂创客圈.
 */
public class ForkJoinTest {

    @org.junit.Test
    public void testAccumulateTask()
            throws ExecutionException, InterruptedException, TimeoutException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        //创建一个累加任务，计算 由1加到10
        AccumulateTask countTask = new AccumulateTask(1, 100);
        Future<Integer> future = forkJoinPool.submit(countTask);
        Integer sum = future.get(1, TimeUnit.SECONDS);
        Print.tcfo("最终的计算结果：" + sum);
        //预期的结果为5050
        Assert.assertTrue(sum == 5050);


    }

    @org.junit.Test
    public void testThreadPoolExecutor() throws ExecutionException, InterruptedException, TimeoutException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                1, //corePoolSize
                100, //maximumPoolSize
                100, //keepAliveTime
                TimeUnit.SECONDS, //unit
                new LinkedBlockingDeque<>(100));//workQueue

        for (int i = 0; i < 5; i++) {
            final int taskIndex = i;
            executor.execute(() ->
            {
                Print.tcfo("taskIndex = " + taskIndex);
                try {
                    Thread.sleep(Long.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        while (true) {
            Print.tcfo("activeCount:" + executor.getActiveCount());
            ThreadUtil.sleepSeconds(1);
        }
    }

    @org.junit.Test
    public void testForkJoinPool() {
        int parallelism = Runtime.getRuntime().availableProcessors();
        Print.tcfo("parallelism = " + parallelism);
        Executor executor = new ForkJoinPool(parallelism,
                ForkJoinPool.defaultForkJoinWorkerThreadFactory,
                null, true);

        for (int i = 0; i < 5; i++) {
            final int taskIndex = i;
            executor.execute(() ->
            {
                Print.tcfo("taskIndex = " + taskIndex);
                try {
                    Thread.sleep(Long.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        while (true) {
            ThreadUtil.sleepSeconds(1);
        }
    }
}

