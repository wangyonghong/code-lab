package me.yonghong.patterns.creational.factorymethod;

import lombok.RequiredArgsConstructor;

/**
 * @author yonghongwang#163.com
 * @since 2021/8/6
 */
@RequiredArgsConstructor
public enum WeaponType {

    /**
     * 武器类型
     */
    SHORT_SWORD("short sword"),
    SPEAR("spear"),
    AXE("axe"),
    UNDEFINED("");

    private final String title;

    @Override
    public String toString() {
        return title;
    }
}
