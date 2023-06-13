package com.nien.demo.basic.use;

import com.nien.demo.util.Print;

/**
 * Created by 尼恩@疯狂创客圈.
 */

public class SuspendDemo {

    public static final int SLEEP_GAP = 500;
    public static final int MAX_TURN = 3;


    public static String getCurThreadName() {
        return Thread.currentThread().getName();
    }


    static class SuspendThread extends Thread {

        static int threadNo = 1;

        public SuspendThread() {
            super("suspendThread-" + threadNo);
            threadNo++;
        }

        public void run() {
            Print.cfo(getName() + " 线程开始挂起.");

            // 线程挂起
//            suspend();
            Print.cfo(getName() + " 线程恢复执行.");

            Print.cfo(getName() + " 运行结束.");
        }

    }


    public static void main(String args[]) throws InterruptedException {

        Thread sThread = new SuspendThread();
        Print.cfo(sThread.getName() + ", 状态为" + sThread.getState());
        sThread.start();

        for (int i = 1; i < MAX_TURN + 3; i++) {
            Print.cfo(sThread.getName() + ", 状态为" + sThread.getState());
            Print.cfo(getCurThreadName() + ", 轮次：" + i);
            Thread.sleep(SLEEP_GAP);
        }
//        sThread.resume();
        Print.cfo(getCurThreadName() + " 运行结束.");
    }


}