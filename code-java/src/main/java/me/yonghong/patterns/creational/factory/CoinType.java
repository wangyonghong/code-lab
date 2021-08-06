package me.yonghong.patterns.creational.factory;

import java.util.function.Supplier;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

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
