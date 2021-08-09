package me.yonghong.patterns.creational.builder;

/**
 * @author yonghongwang#163.com
 * @since 2021/8/9
 */
public enum Profession {

    /**
     * 职业
     */
    WARRIOR,
    THIEF,
    MAGE,
    PRIEST;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
