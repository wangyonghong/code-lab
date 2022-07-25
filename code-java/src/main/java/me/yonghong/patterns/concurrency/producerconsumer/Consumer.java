package me.yonghong.patterns.concurrency.producerconsumer;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yonghongwang#163.com
 * @since 2021/8/6
 */
@Slf4j
public class Consumer {

    private final ItemQueue queue;

    private final String name;

    public Consumer(String name, ItemQueue queue) {
        this.name = name;
        this.queue = queue;
    }

    public void consume() throws InterruptedException {
        Item item = queue.take();
        log.info("Consumer [{}] consume item [{}] produced by [{}]", name,
                item.getId(), item.getProducer());
    }
}