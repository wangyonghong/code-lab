package me.yonghong.springboot.lab.javainaction.chapter02;

import me.yonghong.springboot.lab.javainaction.common.Apple;
import me.yonghong.springboot.lab.javainaction.common.ColorEnum;
import me.yonghong.springboot.lab.javainaction.common.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AppleFilter {

    public static List<Apple> filterApples1(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApples2(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.accept(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static void filterApples(List<Apple> inventory) {
        filterApples2(inventory, new AppleGreenColorPredicate());
        filterApples2(inventory, new AppleHeavyWeightPredicate());
        filterApples2(inventory, new AppleRedAndHeavyPredicate());
        filterApples2(inventory, new ApplePredicate() {
            @Override
            public boolean accept(Apple apple) {
                return apple.getWeight() > 200;
            }
        });
        filterApples2(inventory, apple -> ColorEnum.GREEN.equals(apple.getColor()));
        filterApples2(inventory, apple -> apple.getWeight() > 200);

        filterApples1(inventory, Apple::isGreenApple);
        filterApples1(inventory, Apple::isHeavyApple);

        filterApples1(inventory, apple -> ColorEnum.GREEN.equals(apple.getColor()));
        filterApples1(inventory, apple -> apple.getWeight() > 150);

        inventory.stream()
                .filter(apple -> ColorEnum.GREEN.equals(apple.getColor()))
                .collect(Collectors.toList());
        inventory.parallelStream()
                .filter(apple -> apple.getWeight() > 150)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        filterApples(Inventory.getApple());
    }
}
