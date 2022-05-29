package me.yonghong.demo.thread.juc;


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author yonghongwang#163.com
 * @since 2021/8/4
 **/
public class FutureTaskDemo {

    private static final ExecutorService executor = new ThreadPoolExecutor(5, 10,
            180L, TimeUnit.SECONDS, new PriorityBlockingQueue<Runnable>(15),
            new ThreadFactoryBuilder().setNameFormat("net-%d").build());

    public static void main(String[] args) {
        FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(10000);
                return 100;
            }
        });
        executor.submit(futureTask);
        executor.shutdownNow();
        try {
            futureTask.get(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}