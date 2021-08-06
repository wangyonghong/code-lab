package me.yonghong.patterns.creational.factory;

import lombok.extern.slf4j.Slf4j;

/**
 * 工厂模式
 *
 * @author yonghongwang#163.com
 * @since 2021/8/6
 */
@Slf4j
public class App {

    public static void main(String[] args) {
        log.info("The alchemist begins his work.");
        Coin coin1 = CoinFactory.getCoin(CoinType.COPPER);
        Coin coin2 = CoinFactory.getCoin(CoinType.GOLD);
        log.info(coin1.getDescription());
        log.info(coin2.getDescription());
    }
}
