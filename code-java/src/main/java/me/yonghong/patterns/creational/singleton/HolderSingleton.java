package me.yonghong.patterns.creational.singleton;

/**
 * 静态内部类实现
 *
 * @author yonghongwang#163.com
 * @since 2021/8/6
 */
public class HolderSingleton {

    private HolderSingleton() {
    }

    public static HolderSingleton getInstance() {
        return HelperHolder.INSTANCE;
    }

    private static class HelperHolder {
        private static final HolderSingleton INSTANCE = new HolderSingleton();
    }
}
