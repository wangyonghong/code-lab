package me.yonghong.patterns.creational.singleton;

/**
 * 饥饿式
 *
 * @author yonghongwang#163.com
 * @since 2021/8/6
 */
public class HungrySingleton {

    private HungrySingleton() {
    }

    private static final HungrySingleton INSTANCE = new HungrySingleton();

    public static HungrySingleton getInstance() {
        return INSTANCE;
    }
}
