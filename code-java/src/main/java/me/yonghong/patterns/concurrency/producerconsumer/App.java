package me.yonghong.patterns.concurrency.producerconsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

/**
 * 生产者消费者模式
 *
 * @author yonghongwang#163.com
 * @since 2021/8/6
 */
@Slf4j
public class App {

    public static void main(String[] args) {
        ItemQueue queue = new ItemQueue();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 2; i++) {
            final Producer producer = new Producer("Producer_" + i, queue);
            executorService.submit(() -> {
                while (true) {
                    producer.produce();
                }
            });
        }

        for (int i = 0; i < 3; i++) {
            final Consumer consumer = new Consumer("Consumer_" + i, queue);
            executorService.submit(() -> {
                while (true) {
                    consumer.consume();
                }
            });
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
            executorService.shutdownNow();
        } catch (InterruptedException e) {
            log.error("Error waiting for ExecutorService shutdown");
        }
    }
}

