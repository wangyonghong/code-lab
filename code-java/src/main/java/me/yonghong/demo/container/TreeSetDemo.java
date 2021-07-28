package me.yonghong.demo.container;

import java.util.TreeSet;

/**
 * @author yonghongwang#163.com
 * @since 2021/4/18
 */
public class TreeSetDemo {

    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(20);
        set.add(5);
        set.add(10);
        System.out.println(set.first());

    }
}
