package me.yonghong.patterns.concurrency.producerconsumer;

import java.security.SecureRandom;

/**
 * @author yonghongwang#163.com
 * @since 2021/8/6
 */
public class Producer {

    private static final SecureRandom RANDOM = new SecureRandom();

    private final ItemQueue queue;

    private final String name;

    private int itemId;

    public Producer(String name, ItemQueue queue) {
        this.name = name;
        this.queue = queue;
    }

    public void produce() throws InterruptedException {
        Item item = new Item(name, itemId++);
        queue.put(item);
        Thread.sleep(RANDOM.nextInt(2000));
    }
}
