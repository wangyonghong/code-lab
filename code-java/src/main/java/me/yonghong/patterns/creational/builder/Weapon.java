package me.yonghong.patterns.creational.builder;

/**
 * @author yonghongwang#163.com
 * @since 2021/8/9
 */
public enum Weapon {

    /**
     * 武器
     */
    DAGGER,
    SWORD,
    AXE,
    WAR_HAMMER,
    BOW;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
