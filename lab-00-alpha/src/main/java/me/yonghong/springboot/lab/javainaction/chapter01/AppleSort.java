package me.yonghong.springboot.lab.javainaction.chapter01;

import me.yonghong.springboot.lab.javainaction.common.Apple;
import me.yonghong.springboot.lab.javainaction.common.Inventory;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AppleSort {

    /**
     * see {@link List#sort(Comparator)} 默认实现方法
     */
    public static void sort(List<Apple> inventory) {
        // new
        inventory.sort(Comparator.comparing(Apple::getWeight));
        // old
        Collections.sort(inventory, new Comparator<Apple>() {
            @Override
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight().compareTo(a2.getWeight());
            }
        });
    }

    public static void main(String[] args) {
        sort(Inventory.getApple());
    }
}
