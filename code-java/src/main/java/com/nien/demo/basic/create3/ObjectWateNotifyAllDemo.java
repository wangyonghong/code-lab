package com.nien.demo.basic.create3;

import com.nien.demo.util.JvmUtil;
import com.nien.demo.util.Print;

/**
 * Created by 尼恩@疯狂创客圈.
 */

public class ObjectWateNotifyAllDemo {

    public static final int SLEEP_GAP = 30 * 1000;
    public static Object locko = new Object();

    static class ObjectWaitThread extends Thread {

        static int threadNO = 1;//线程编号

        public ObjectWaitThread() {
            super("objectWaitThread-" + threadNO);
            threadNO++;
        }


        public void run() {

            Print.tcfo("争夺监视锁");
            synchronized (locko) {
                Print.tcfo("争夺监视锁成功,等待被唤醒");
                try {
                    locko.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Print.tcfo("收到通知，开始执行死循环");
                for (; ; ) {

                }
            }
        }
    }

    public static void main(String args[]) throws InterruptedException {
        Print.cfo("当前进程的ID是" + JvmUtil.getProcessID());

        //演示WAITING状态的线程
        for (int i = 0; i < 4; i++) {
            new ObjectWaitThread().start();
        }

        Thread.sleep(1000);


        Thread notifyThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Print.tcfo("争夺监视锁");

                synchronized (locko) {
                    Print.tcfo("争夺监视锁成功");
                    Print.tcfo("通知一条等待线程");
                    locko.notifyAll();
                    try {
                        Thread.sleep(SLEEP_GAP);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Print.tcfo("同步块执行完成");
                }
            }
        }, "notifyThread");
        notifyThread.start();

    }

}