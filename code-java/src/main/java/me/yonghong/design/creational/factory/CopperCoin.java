package me.yonghong.design.creational.factory;

/**
 * @author yonghongwang#163.com
 * @since 2021/8/5
 */
public class CopperCoin implements Coin {

    static final String DESCRIPTION = "This is a copper coin.";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
