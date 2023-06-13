package com.nien.demo.basic.create;

import com.nien.demo.util.Print;
import com.nien.demo.util.ThreadUtil;

/**
 * Created by 尼恩@疯狂创客圈.
 */

public class ScheduleDemo2 {

    public static final int MAX_TURN = 50;


    public static Thread getCurThread() {
        return Thread.currentThread();
    }

    public static int getCurPriority() {
        return Thread.currentThread().getPriority();
    }

    static int threadNo = 1;

    static class DemoThread extends Thread {

        public DemoThread() {
            super("Mall-" + threadNo++);
        }

        public void run() {
            long startTime = System.currentTimeMillis();

            for (int j = 1; j < MAX_TURN; j++) {
                long used = System.currentTimeMillis() - startTime;
                Print.cfo(getName() + ", 运行时间：" + used);

            }
            Print.cfo(getName() + " 运行结束.");
        }
    }

    public static void main(String args[]) throws InterruptedException {
        Thread thread = null;


        for (int i = 0; i < 20; i++) {
            thread = new DemoThread();
            if (i > 10) thread.setPriority(Thread.MAX_PRIORITY);
            thread.start();
        }


        Print.cfo(ThreadUtil.getCurThreadName() + " 运行结束.");
    }
}