package com.nien.demo.basic.create3;

import com.nien.demo.util.Print;
import com.nien.demo.util.RandomUtil;
import com.nien.demo.util.ThreadUtil;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 尼恩@疯狂创客圈.
 */

public class CreateThreadPoolDemo {

    public static final int SLEEP_GAP = 500;
    public static final int MAX_TURN = 5;

    //异步的执行目标类
    public static class TargetTask implements Runnable {
        static AtomicInteger taskNo = new AtomicInteger(1);
        protected String taskName;

        public TargetTask() {
            taskName = "task-" + taskNo.get();
            taskNo.incrementAndGet();
        }

        public void run() {

            Print.tco("任务：" + taskName + " doing");
            // 线程睡眠一会
            ThreadUtil.sleepMilliSeconds(SLEEP_GAP);

            Print.tco(taskName + " 运行结束.");
        }

        @Override
        public String toString() {
            return "TargetTask{" + taskName + '}';
        }
    }

    //异步的执行目标类：执行过程中将发生异常
    static class TargetTaskWithError extends TargetTask {
        public void run() {
            super.run();
            throw new RuntimeException("Error from " + taskName);
        }
    }

    //测试用例：只有一条线程的线程池
    @Test
    public void testSingleThreadExecutor() {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            pool.execute(new TargetTask());
            pool.submit(new TargetTask());
        }
        ThreadUtil.sleepSeconds(1000);
        //关闭线程池
        pool.shutdown();
    }


    //测试用例：只有3条线程固定大小的线程池
    @Test
    public void testNewFixedThreadPool() {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            pool.execute(new TargetTask());
            pool.submit(new TargetTask());
        }
        ThreadUtil.sleepSeconds(1000);
        //关闭线程池
        pool.shutdown();
    }

    //测试用例：“可缓存线程池”
    @Test
    public void testNewCacheThreadPool() {
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            pool.execute(new TargetTask());
            pool.submit(new TargetTask());
        }
        ThreadUtil.sleepSeconds(1000);
        //关闭线程池
        pool.shutdown();
    }

    //测试用例：“可调度线程池”
    @Test
    public void testNewScheduledThreadPool() {
        ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(2);
        for (int i = 0; i < 2; i++) {
            scheduled.scheduleAtFixedRate(new TargetTask(),
                    0, 500, TimeUnit.MILLISECONDS);
            //以上的参数中：
            // 0表示首次执行任务的延迟时间，500表示每次执行任务的间隔时间
            //TimeUnit.MILLISECONDS所设置的时间的计时单位为毫秒
        }
        ThreadUtil.sleepSeconds(1000);
        //关闭线程池
        scheduled.shutdown();
    }

    //测试用例：“可调度线程池2”
    @Test
    public void testNewScheduledThreadPool2() {
        ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(0);
        for (int i = 0; i < 2; i++) {
            scheduled.scheduleAtFixedRate(new TargetTask(),
                    0, 500, TimeUnit.MILLISECONDS);
            //以上的参数中：
            // 0表示首次执行任务的延迟时间，500表示每次执行任务的间隔时间
            //TimeUnit.MILLISECONDS所设置的时间的计时单位为毫秒
        }
        ThreadUtil.sleepSeconds(1000);
        //关闭线程池
        scheduled.shutdown();
    }


    @Test
    public void testThreadPoolExecutor() {
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
                Print.tco("taskIndex = " + taskIndex);
                try {
                    Thread.sleep(Long.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        while (true) {
            Print.tco("- activeCount:" + executor.getActiveCount() +
                    " - taskCount:" + executor.getTaskCount());
            ThreadUtil.sleepSeconds(1);
        }
    }

    //一个简单的线程工厂
    static public class SimpleThreadFactory implements ThreadFactory {
        static AtomicInteger threadNo = new AtomicInteger(1);

        //实现其唯一的创建线程方法
        @Override
        public Thread newThread(Runnable target) {
            String threadName = "simpleThread-" + threadNo.get();
            Print.tco("创建一条线程，名称为：" + threadName);
            threadNo.incrementAndGet();
            //设置线程名称
            Thread thread = new Thread(target, threadName);
            //设置为守护线程
            thread.setDaemon(true);
            return thread;
        }
    }


    @Test
    public void testThreadFactory() {
        //使用自定义线程工厂，快捷创建线程池
        ExecutorService pool =
                Executors.newFixedThreadPool(2, new SimpleThreadFactory());
        for (int i = 0; i < 5; i++) {
            pool.submit(new TargetTask());
        }
        //等待10秒
        ThreadUtil.sleepSeconds(10);
        Print.tco("关闭线程池");
        pool.shutdown();
    }

    //自定义拒绝策略
    public static class CustomIgnorePolicy implements RejectedExecutionHandler {
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            // 可做日志记录等
            Print.tco(r + " rejected; " + " - getTaskCount: " + e.getTaskCount());
        }
    }

    @Test
    public void testCustomIgnorePolicy() {
        int corePoolSize = 2; //核心线程数
        int maximumPoolSize = 4;  //最大线程数
        long keepAliveTime = 10;
        TimeUnit unit = TimeUnit.SECONDS;
        //最大排队任务数
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        //线程工厂
        ThreadFactory threadFactory = new SimpleThreadFactory();
        //拒绝和异常策略
        RejectedExecutionHandler policy = new CustomIgnorePolicy();
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime, unit,
                workQueue,
                threadFactory,
                policy);

        // 预启动所有核心线程
        pool.prestartAllCoreThreads();
        for (int i = 1; i <= 10; i++) {
            pool.execute(new TargetTask());
        }
        //等待10秒
        ThreadUtil.sleepSeconds(10);
        Print.tco("关闭线程池");
        pool.shutdown();
    }

    //线程本地变量,用于记录线程异步任务的开始执行时间
    private static final ThreadLocal<Long> START_TIME = new ThreadLocal<>();

    @Test
    public void testHooks() {
        ExecutorService pool = new ThreadPoolExecutor(2,
                4, 60,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(2)) {
            @Override
            protected void terminated() {
                Print.tco("调度器已经终止!");
            }

            @Override
            protected void beforeExecute(Thread t, Runnable target) {
                Print.tco(target + "前钩子被执行");
                //记录开始执行时间
                START_TIME.set(System.currentTimeMillis());
                super.beforeExecute(t, target);
            }


            @Override
            protected void afterExecute(Runnable target, Throwable t) {
                super.afterExecute(target, t);
                //计算执行时长
                long time = (System.currentTimeMillis() - START_TIME.get());
                Print.tco(target + " 后钩子被执行, 任务执行时长（ms）：" + time);
                //清空本地变量
                START_TIME.remove();
            }
        };


        pool.execute(new TargetTask());

        //等待10秒
        ThreadUtil.sleepSeconds(10);
        Print.tco("关闭线程池");
        pool.shutdown();

    }


    @Test
    public void testNewFixedThreadPool2() {
        //创建一个固定大小线程池
        ExecutorService fixedExecutorService = Executors.newFixedThreadPool(1);
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) fixedExecutorService;
        Print.tco(threadPoolExecutor.getMaximumPoolSize());
        //设置核心线程数
        threadPoolExecutor.setCorePoolSize(8);

        //创建一个单线程化的线程池
        ExecutorService singleExecutorService = Executors.newSingleThreadExecutor();
        //转换成普通线程池， 会抛出运行时异常 java.lang.ClassCastException
        ((ThreadPoolExecutor) singleExecutorService).setCorePoolSize(8);
    }


    //测试用例：提交和执行
    @Test
    public void testSubmit() {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
        pool.execute(new TargetTaskWithError());
        /**
         * submit(Runnable x) 返回一个future。可以用这个future来判断任务是否成功完成。请看下面：
         */
        Future future = pool.submit(new TargetTaskWithError());

        try {
            //如果异常抛出，会在调用Future.get()时传递给调用者
            if (future.get() == null) {
                //如果Future的返回为null，任务完成
                Print.tco("任务完成");
            }
        } catch (Exception e) {
            Print.tco(e.getCause().getMessage());
        }


        ThreadUtil.sleepSeconds(10);
        //关闭线程池
        pool.shutdown();
    }

    //测试用例：获取异步调用的结果
    @Test
    public void testSubmit2() {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
        Future<Integer> future = pool.schedule(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                //返回200 - 300 之间的随机数
                return RandomUtil.randInRange(200, 300);
            }
        }, 100, TimeUnit.MILLISECONDS);

        try {
            Integer result = future.get();
            Print.tco("异步执行的结果是:" + result);
        } catch (InterruptedException e) {
            Print.tco("异步调用被中断");
            e.printStackTrace();
        } catch (ExecutionException e) {
            Print.tco("异步调用过程中，发生了异常");
            e.printStackTrace();
        }
        ThreadUtil.sleepSeconds(10);
        //关闭线程池
        pool.shutdown();

    }


    //测试用例：优雅关闭
    @Test
    public void testShutdownGracefully() {
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(2);
        threadPool.shutdown(); // Disable new tasks from being submitted
        try {
            // 设定最大重试次数
            // 等待 60 s
            if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                // 调用 shutdownNow 取消正在执行的任务
                threadPool.shutdownNow();
                // 再次等待 60 s，如果还未结束，可以再次尝试，或则直接放弃
                if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                    System.err.println("线程池任务未正常执行结束");
                }
            }
        } catch (InterruptedException ie) {
            // 重新调用 shutdownNow
            threadPool.shutdownNow();
        }
    }


}

