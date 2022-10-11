package me.yonghong.patterns.structural.flyweight;

import com.google.common.cache.Cache;

/**
 * @author yonghongwang#163.com
 * @link <a href="https://java-design-patterns.com/patterns/flyweight/"></a>
 * @see Integer#valueOf(int)
 * @see Byte#valueOf(byte)
 * @see Cache
 * @since 2021/8/6
 */
public class App {

    public static void main(String[] args) {
        AlchemistShop alchemistShop = new AlchemistShop();
        alchemistShop.drinkPotions();
    }
}
