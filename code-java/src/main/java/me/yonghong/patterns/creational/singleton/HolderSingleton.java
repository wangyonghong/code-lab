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
        // 静态变量初始化在 clinit 方法中，JVM 规范中规定 clinit 必须是线程安全的，JVM 实现中加锁保证
        private static final HolderSingleton INSTANCE = new HolderSingleton();
    }
}
