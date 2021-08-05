package me.yonghong.design.creational.factory;

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

    COPPER(CopperCoin::new),
    GOLD(GoldCoin::new);

    private final Supplier<Coin> constructor;

}
