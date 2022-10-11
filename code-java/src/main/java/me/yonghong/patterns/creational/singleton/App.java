package me.yonghong.patterns.creational.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * 单例模式
 *
 * @author yonghongwang#163.com
 * @link <a href="https://java-design-patterns.com/patterns/singleton/"></a>
 * @see java.lang.Runtime#getRuntime()
 * @see java.lang.System#getSecurityManager()
 * @since 2021/8/6
 */
@Slf4j
public class App {

    public static void main(String[] args) {

        // eagerly initialized singleton
        var hungrySingleton1 = HungrySingleton.getInstance();
        var hungrySingleton2 = HungrySingleton.getInstance();
        log.info("hungrySingleton1={}", hungrySingleton1);
        log.info("hungrySingleton2={}", hungrySingleton2);

        // enum singleton
        var enumSingleton1 = EnumSingleton.INSTANCE;
        var enumSingleton2 = EnumSingleton.INSTANCE;
        log.info("enumSingleton1={}", enumSingleton1);
        log.info("enumSingleton2={}", enumSingleton2);

        // double checked locking singleton
        var doubleCheckLockingSingleton1 = DoubleCheckLockingSingleton.getInstance();
        log.info(doubleCheckLockingSingleton1.toString());
        var doubleCheckLockingSingleton2 = DoubleCheckLockingSingleton.getInstance();
        log.info(doubleCheckLockingSingleton2.toString());

        // static holder singleton
        var holderSingleton1 = HolderSingleton.getInstance();
        log.info(holderSingleton1.toString());
        var holderSingleton2 = HolderSingleton.getInstance();
        log.info(holderSingleton2.toString());
    }
}
