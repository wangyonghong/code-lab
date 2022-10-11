package me.yonghong.patterns.creational.simplefactory;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yonghongwang#163.com
 * @since 2022/10/11
 */
@Slf4j
public class App {

    public static void main(String[] args) {
        log.info("The alchemist begins his work.");
        Coin coin1 = CoinFactory.getCoin("copper");
        Coin coin2 = CoinFactory.getCoin("gold");
        log.info(coin1.getDescription());
        log.info(coin2.getDescription());
    }
}
