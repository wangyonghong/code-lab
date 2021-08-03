package me.yonghong.demo.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yonghongwang#163.com
 * @link <a href="https://zhuanlan.zhihu.com/p/270183612"></a>
 * @since 2021/8/3
 */
public class PrintAbcTest {

    private final Lock reentrantLock = new ReentrantLock();
    private final Condition c1 = reentrantLock.newCondition();
    private final Condition c2 = reentrantLock.newCondition();
    private final Condition c3 = reentrantLock.newCondition();


    private final Object objectLock = new Object();

    // 控制打印次数
    private int times = 100;
    // 当前状态值：保证三个线程之间交替打印
    private int state;

    public static void main(String[] args) {
        new PrintAbcTest().test3();
    }

    public void test1() {
        new Thread(() -> printLetterWithLock("A", 0), "A").start();

        new Thread(() -> printLetterWithLock("B", 1), "B").start();

        new Thread(() -> printLetterWithLock("C", 2), "C").start();
    }

    public void test2() {
        new Thread(() -> printLetterWithSynchronized("A", 0), "A").start();

        new Thread(() -> printLetterWithSynchronized("B", 1), "B").start();

        new Thread(() -> printLetterWithSynchronized("C", 2), "C").start();
    }

    public void test3() {
        new Thread(() -> printLetterWithCondition("A", 0, c1, c2), "A").start();

        new Thread(() -> printLetterWithCondition("B", 1, c2, c3), "B").start();

        new Thread(() -> printLetterWithCondition("C", 2, c3, c1), "C").start();
    }

    private void printLetterWithLock(String name, int targetNum) {
        for (int i = 0; i < times; ) {
            reentrantLock.lock();
            if (state % 3 == targetNum) {
                state++;
                i++;
                System.out.print(name);
            }
            reentrantLock.unlock();
        }
    }

    private void printLetterWithSynchronized(String name, int targetNum) {
        for (int i = 0; i < times; ) {
            synchronized (objectLock) {
                if (state % 3 == targetNum) {
                    state++;
                    i++;
                    System.out.print(name);
                    objectLock.notifyAll();
                } else {
                    try {
                        objectLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void printLetterWithCondition(String name, int targetNum, Condition current, Condition next) {
        for (int i = 0; i < times; ) {
            reentrantLock.lock();
            try {
                if (state % 3 == targetNum) {
                    state++;
                    i++;
                    System.out.print(name);
                    next.signal();
                } else {
                    current.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

}
