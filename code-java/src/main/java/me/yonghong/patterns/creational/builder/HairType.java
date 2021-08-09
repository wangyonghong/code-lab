package me.yonghong.patterns.creational.builder;

import lombok.AllArgsConstructor;

/**
 * @author yonghongwang#163.com
 * @since 2021/8/9
 */
@AllArgsConstructor
public enum HairType {

    /**
     * 发型
     */
    BALD("bald"),
    SHORT("short"),
    CURLY("curly"),
    LONG_STRAIGHT("long straight"),
    LONG_CURLY("long curly");

    private final String title;

    @Override
    public String toString() {
        return title;
    }
}
