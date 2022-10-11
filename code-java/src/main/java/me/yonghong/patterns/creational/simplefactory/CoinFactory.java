package me.yonghong.patterns.creational.simplefactory;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yonghongwang#163.com
 * @since 2021/8/5
 */
@Slf4j
public class CoinFactory {

    public static Coin getCoin(String type) {
        switch (type) {
            case "copper":
                return new CopperCoin();
            case "gold":
                return new GoldCoin();
            default:
                break;
        }
        return null;
    }
}
