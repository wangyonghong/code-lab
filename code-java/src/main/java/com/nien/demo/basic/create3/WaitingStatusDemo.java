package com.nien.demo.basic.create3;

import com.nien.demo.util.JvmUtil;
import com.nien.demo.util.Print;

/**
 * Created by 尼恩@疯狂创客圈.
 */

public class WaitingStatusDemo {

    public static final int SLEEP_GAP = 500 * 1000;
    public static final int MAX_TURN = 3;


    public static String getCurThreadName() {
        return Thread.currentThread().getName();
    }


    public static void main(String args[]) throws InterruptedException {
        Print.cfo("当前进程的ID是" + JvmUtil.getProcessID());


        //演示一条TIMED_WAITING状态的线程，调用sleep(long)实例方法
        Thread timeWaitingThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(SLEEP_GAP);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "timeWaitingThread");
        timeWaitingThread.start();

        //演示一条RUNNABLE状态的线程，调用sleep(long)实例方法
        Thread runnableThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                    int i = 1;
                    i++;
                }
            }
        }, "runnableThread");
        runnableThread.start();

        //演示一条WAITING状态的线程，调用sleep(long)实例方法
        Thread waitingThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    runnableThread.join(SLEEP_GAP);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "waitingThread");
        waitingThread.start();
    }

}