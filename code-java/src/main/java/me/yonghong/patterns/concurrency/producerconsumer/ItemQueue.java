package me.yonghong.patterns.concurrency.producerconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author yonghongwang#163.com
 * @since 2021/8/6
 */
public class ItemQueue {

    private final BlockingQueue<Item> queue;

    public ItemQueue() {
        queue = new LinkedBlockingQueue<>(5);
    }

    public void put(Item item) throws InterruptedException {
        queue.put(item);
    }

    public Item take() throws InterruptedException {
        return queue.take();
    }

}
