package me.yonghong.patterns.creational.simplefactory;

/**
 * @author yonghongwang#163.com
 * @since 2021/8/5
 */
public class CopperCoin implements Coin {

    private static final String DESCRIPTION = "This is a copper coin.";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
