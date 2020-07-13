package me.yonghong.springboot.lab.javainaction.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Apple {

    private Integer weight;

    private ColorEnum color;

    public static boolean isGreenApple(Apple apple) {
        return ColorEnum.GREEN.equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }
}
