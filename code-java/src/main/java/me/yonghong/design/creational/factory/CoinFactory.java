package me.yonghong.design.creational.factory;

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

    public static void main(String[] args) {
        log.info("The alchemist begins his work.");
        log.debug("The alchemist begins his work.");

        Coin coin1 = CoinFactory.getCoin(CoinType.COPPER);
        Coin coin2 = CoinFactory.getCoin(CoinType.GOLD);
        log.info(coin1.getDescription());
        log.info(coin2.getDescription());
    }
}
