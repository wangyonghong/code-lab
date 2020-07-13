package me.yonghong.springboot.lab.javainaction.chapter02;

import me.yonghong.springboot.lab.javainaction.common.Apple;

@FunctionalInterface
public interface AppleFormatter {

    String accept(Apple a);
}
