package me.yonghong.springboot.lab.javainaction.chapter02;

import me.yonghong.springboot.lab.javainaction.common.Apple;
import me.yonghong.springboot.lab.javainaction.common.Inventory;

import java.util.Comparator;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LambdaExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Inventory.getApple().sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
        Inventory.getApple().sort(Comparator.comparing(Apple::getWeight));

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world!");
            }
        });
        Thread t2 = new Thread(() -> System.out.println("hello world!"));

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> t3 = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return Thread.currentThread().getName();
            }
        });
        Future<String> t4 = executorService.submit(() -> Thread.currentThread().getName());

        System.out.println(t4.get());
        executorService.shutdown();
    }
}
