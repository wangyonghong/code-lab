package me.yonghong.demo.thread.juc;

import com.google.common.base.Stopwatch;

import java.util.concurrent.*;

/**
 * @author yonghongwang#163.com
 * @since 2021/11/10
 **/
public class ThreadPoolTimeTest {

    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        ExecutorService executorService1 = Executors.newCachedThreadPool(r -> new Thread(r, "test"));
        System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));
        stopwatch.reset().start();
        ExecutorService executorService2 = Executors.newSingleThreadExecutor(r -> new Thread(r, "test"));
        System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));
        stopwatch.reset().start();
        ExecutorService executorService3 = Executors.newSingleThreadScheduledExecutor(r -> new Thread(r, "test"));
        System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

}
