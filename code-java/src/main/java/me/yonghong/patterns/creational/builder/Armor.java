package me.yonghong.patterns.creational.builder;

import lombok.AllArgsConstructor;

/**
 * 盔甲类型
 *
 * @author yonghongwang#163.com
 * @since 2021/8/9
 */
@AllArgsConstructor
public enum Armor {

    /**
     * 盔甲类型
     */
    CLOTHES("clothes"),
    LEATHER("leather"),
    CHAIN_MAIL("chain mail"),
    PLATE_MAIL("plate mail");

    private final String title;

    @Override
    public String toString() {
        return title;
    }
}
