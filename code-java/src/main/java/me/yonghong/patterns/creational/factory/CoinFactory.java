package me.yonghong.patterns.creational.factory;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yonghongwang#163.com
 * @since 2021/8/5
 */
@Slf4j
public class CoinFactory {

    public static Coin getCoin(CoinType type) {
        return type.getConstructor().get();
    }
}
