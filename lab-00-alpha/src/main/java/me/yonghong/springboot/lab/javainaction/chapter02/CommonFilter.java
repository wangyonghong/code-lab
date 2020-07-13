package me.yonghong.springboot.lab.javainaction.chapter02;

import me.yonghong.springboot.lab.javainaction.common.Apple;
import me.yonghong.springboot.lab.javainaction.common.ColorEnum;
import me.yonghong.springboot.lab.javainaction.common.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class CommonFilter<T> {

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (p.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Apple> redApple = filter(Inventory.getApple(), apple -> ColorEnum.RED.equals(apple.getColor()));
        List<Integer> numbers = filter(Inventory.getNumbers(), n -> n > 5);
    }
}
