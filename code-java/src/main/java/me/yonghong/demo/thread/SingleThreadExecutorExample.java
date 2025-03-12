package me.yonghong.demo.thread;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SingleThreadExecutorExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // 提交第一个任务（模拟耗时任务）
        Future<String> future1 = executor.submit(() -> {
            Thread.sleep(5000); // 模拟任务耗时 5 秒
            return "Task 1 Completed";
        });

        try {
            // 等待 2 秒获取结果
            System.out.println("Result: " + future1.get(2, TimeUnit.SECONDS));
        } catch (TimeoutException e) {
            System.out.println("Timeout: Task 1 did not complete in time.");
            System.out.println(new Date());
            future1.cancel(true);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

        // 提交第二个任务
        Future<String> future2 = executor.submit(() -> {
            return "Task 2 Completed";
        });

        try {
            // 获取第二个任务的结果
            System.out.println("Result: " + future2.get());
            System.out.println(new Date());
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            executor.shutdown();
        }
    }
}
