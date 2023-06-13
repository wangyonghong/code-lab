package com.nien.demo.basic.create3;

import com.nien.demo.util.Print;
import com.nien.demo.util.ThreadUtil;

/**
 * Created by 尼恩@疯狂创客圈.
 */

public class RunnableDemo {

    public static final int SLEEP_GAP = 500;
    public static final long MAX_TURN = 1000;
    static int turn = 0;

    static class RunnableThread extends Thread {

        public RunnableThread() {
            super("runnableThread");
        }

        public void run() {
            Print.cfo(getName() + " 运行开始.");
            Print.cfo(getName() + ", 状态为" + getState());

            for (; turn < MAX_TURN; turn++) {
                Print.cfo(getName() +
                        "- turn -" + turn +
                        "- 状态为" + getState());
            }

            Print.cfo(getName() + "- 运行结束.");

        }

    }

    public static void main(String args[]) throws InterruptedException {


        Thread sThread = new RunnableThread();
        Print.cfo(sThread.getName() + "- 状态为" + sThread.getState());

        sThread.start();

        for (; turn < MAX_TURN; turn++) {
            Print.cfo(sThread.getName() +
                    "- turn -" + turn +
                    "- 状态为" + sThread.getState());
        }


        Thread.sleep(SLEEP_GAP);
        Print.cfo(sThread.getName() + "- 状态为" + sThread.getState());

        Print.cfo(ThreadUtil.getCurThreadName() + "- 运行结束.");
    }


}