package me.yonghong.springboot.lab.javainaction.common;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    public static List<Apple> getApple() {
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple(100, ColorEnum.GREEN));
        apples.add(new Apple(150, ColorEnum.GREEN));
        apples.add(new Apple(200, ColorEnum.GREEN));
        apples.add(new Apple(250, ColorEnum.GREEN));
        apples.add(new Apple(100, ColorEnum.RED));
        apples.add(new Apple(150, ColorEnum.RED));
        apples.add(new Apple(200, ColorEnum.RED));
        apples.add(new Apple(250, ColorEnum.RED));
        apples.add(new Apple(100, ColorEnum.YELLOW));
        apples.add(new Apple(150, ColorEnum.YELLOW));
        apples.add(new Apple(200, ColorEnum.YELLOW));
        apples.add(new Apple(250, ColorEnum.YELLOW));
        return apples;
    }

    public static List<Integer> getNumbers() {
        return new ArrayList<>(List.of(1, 3, 5, 7, 9, 2, 4, 6, 8, 10));
    }
}
