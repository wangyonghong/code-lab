package me.yonghong.patterns.creational.builder;

/**
 * @author yonghongwang#163.com
 * @since 2021/8/9
 */
public enum HairColor {

    /**
     * 发色
     */
    WHITE,
    BLOND,
    RED,
    BROWN,
    BLACK;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

}
