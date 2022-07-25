package me.yonghong.patterns.creational.factory;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

/**
 * @author yonghongwang#163.com
 * @since 2021/8/5
 */
@RequiredArgsConstructor
@Getter
public enum CoinType {

    /**
     * 铜币
     */
    COPPER(CopperCoin::new),
    /**
     * 金币
     */
    GOLD(GoldCoin::new);

    private final Supplier<Coin> constructor;

}
