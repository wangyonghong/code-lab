package me.yonghong.patterns.creational.factory;

/**
 * @author yonghongwang#163.com
 * @since 2021/8/5
 */
public class GoldCoin implements Coin {

    static final String DESCRIPTION = "This is a gold coin.";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
