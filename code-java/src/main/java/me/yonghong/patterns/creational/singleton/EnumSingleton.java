package me.yonghong.patterns.creational.singleton;

/**
 * 枚举实现
 *
 * @author yonghongwang#163.com
 * @since 2021/8/6
 */
public enum EnumSingleton {

    /**
     * 单例本例
     */
    INSTANCE;

    @Override
    public String toString() {
        return getDeclaringClass().getCanonicalName() + "@" + hashCode();
    }
}
