package me.yonghong.patterns.concurrency.producerconsumer;

import lombok.Data;

/**
 * @author yonghongwang#163.com
 * @since 2021/8/6
 */
@Data
public class Item {

    private final String producer;

    private final int id;

    public Item(String producer, int id) {
        this.id = id;
        this.producer = producer;
    }

}
