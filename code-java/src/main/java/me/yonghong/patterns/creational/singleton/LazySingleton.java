package me.yonghong.patterns.creational.singleton;

import java.util.concurrent.CountDownLatch;

/**
 * 懒汉式
 *
 * @author yonghongwang#163.com
 * @since 2020/12/10
 **/
public class LazySingleton {

    // synchronized和Lock也能够保证可见性，synchronized和Lock能保证同一时刻只有一个线程获取锁然后执行同步代码，
    // 并且在释放锁之前会将对变量的修改刷新到主存当中
    // 因此此处并不需要加 volatile
    private static LazySingleton instance;

    private LazySingleton() {
    }

    public synchronized static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

    public static void main(String[] args) {
        final LazySingleton[] instance = new LazySingleton[2];
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread() {
            @Override
            public void run() {
                instance[0] = LazySingleton.getInstance();
                countDownLatch.countDown();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                instance[1] = LazySingleton.getInstance();
                countDownLatch.countDown();
            }
        }.start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(instance[0].hashCode() == instance[1].hashCode());
    }
}
