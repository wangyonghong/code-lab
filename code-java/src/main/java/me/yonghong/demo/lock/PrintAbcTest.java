package me.yonghong.demo.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yonghongwang#163.com
 * @since 2021/8/3
 */
public class PrintAbcTest {
    // 控制打印次数
    private int times;
    // 当前状态值：保证三个线程之间交替打印
    private int state;
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        new PrintAbcTest().test();
    }

    public void test() {
        new Thread(() -> printLetter("A", 0), "A").start();

        new Thread(() -> printLetter("B", 1), "B").start();

        new Thread(() -> printLetter("C", 2), "C").start();
    }

    private void printLetter(String name, int targetNum) {
        for (int i = 0; i < times; ) {
            lock.lock();
            if (state % 3 == targetNum) {
                state++;
                i++;
                System.out.print(name);
            }
            lock.unlock();
        }
    }

}
