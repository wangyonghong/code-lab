package me.yonghong.demo.thread;

import java.util.concurrent.*;

/**
 * @author yonghongwang#163.com
 */
public class ThreadTest {

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(
                16, 1000,
                15, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        executorService.submit(() -> System.out.println("test"));
    }
}
