package me.yonghong.patterns.creational.singleton;

/**
 * 懒汉式-双重锁校验
 *
 * @author yonghongwang#163.com
 * @since 2020/12/10
 **/
public class DoubleCheckLockingSingleton {

    private static volatile DoubleCheckLockingSingleton instance;

    private static volatile boolean flag = true;

    private DoubleCheckLockingSingleton() {
        if (flag) {
            flag = false;
        } else {
            throw new IllegalStateException("Already initialized.");
        }
    }

    public static DoubleCheckLockingSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckLockingSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckLockingSingleton();
                }
            }
        }
        return instance;
    }
}
