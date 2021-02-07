package me.yonghong.interview.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yonghongwang#163.com
 * @since 2020/12/16
 **/
public class OddEvenPrinterEx {

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    private final int limit;
    private volatile int count;

    public OddEvenPrinterEx(int limit, int initCount) {
        this.limit = limit;
        this.count = initCount;
    }

    public void print() {
        lock.lock();
        try {
            while (count < limit) {
                System.out.printf("线程[%s]打印数字:%d%n", Thread.currentThread().getName(), ++count);
                condition.signalAll();
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    //ignore
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws Exception {
        OddEvenPrinterEx printer = new OddEvenPrinterEx(10, 0);
        Thread thread1 = new Thread(printer::print, "thread-1");
        Thread thread2 = new Thread(printer::print, "thread-2");
        thread1.start();
        thread2.start();
        Thread.sleep(Integer.MAX_VALUE);
    }
}
