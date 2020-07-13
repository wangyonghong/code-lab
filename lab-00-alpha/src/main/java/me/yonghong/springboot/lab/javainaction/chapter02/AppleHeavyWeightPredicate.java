package me.yonghong.springboot.lab.javainaction.chapter02;

import me.yonghong.springboot.lab.javainaction.common.Apple;

public class AppleHeavyWeightPredicate implements ApplePredicate {

    @Override
    public boolean accept(Apple apple) {
        return apple.getWeight() > 150;
    }
}
