package com.nien.demo.basic.create3;

import com.nien.demo.util.JvmUtil;
import com.nien.demo.util.Print;

/**
 * Created by 尼恩@疯狂创客圈.
 */

public class BlockedStatusDemo {

    public static final int SLEEP_GAP = 500 * 1000;
    public static final int MAX_TURN = 3;
    public static Object locko = new Object();

    static class BlockedThread extends Thread {

        static int threadNO = 1;//线程编号

        public BlockedThread() {
            super("blockedThread-" + threadNO);
            threadNO++;
        }


        public void run() {

            Print.tcfo("等待获取locko的监视锁");
            synchronized (locko) {
                Print.tcfo("获取locko的监视锁成功");
            }
        }
    }

    public static void main(String args[]) throws InterruptedException {
        Print.cfo("当前进程的ID是" + JvmUtil.getProcessID());

        Thread runnableThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Print.tcfo("等待locko的获取监视锁");

                synchronized (locko) {
                    Print.tcfo("获取locko的监视锁成功");
                    for (; ; ) {
                        int i = 1;
                        i++;
                    }
                }
            }
        }, "runnableThread");
        runnableThread.start();

        Thread.sleep(1000);

        //演示BLOCKED状态的线程，调用sleep(long)实例方法
        for (int i = 0; i < 4; i++) {
            new BlockedThread().start();
        }


    }

}