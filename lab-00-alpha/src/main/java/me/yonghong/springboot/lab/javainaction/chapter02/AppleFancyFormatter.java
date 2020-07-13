package me.yonghong.springboot.lab.javainaction.chapter02;

import me.yonghong.springboot.lab.javainaction.common.Apple;

public class AppleFancyFormatter implements AppleFormatter {

    @Override
    public String accept(Apple apple) {
        String characteristic = apple.getWeight() > 150 ? "heavy" : "light";
        return "A " + characteristic + " " + apple.getColor() + " apple";
    }
}
