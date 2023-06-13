package com.nien.demo.basic.create3;

import com.nien.demo.util.Print;
import com.nien.demo.util.ThreadUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 尼恩@疯狂创客圈.
 */

public class StatusDemo {

    //每个线程执行的轮次
    public static final long MAX_TURN = 5;


    //线程编号
    static int threadSeqNumber = 0;

    //全局的静态线程列表
    static List<Thread> threadList = new ArrayList<>();

    //输出静态线程列表中，所有线程的状态
    private static void printThreadStatus() {
        for (Thread thread : threadList) {
            Print.tco(thread.getName() + " 状态为 " + thread.getState());

        }
    }

    //向全局的静态线程列表加入线程
    private static void addStatusThread(Thread thread) {
        threadList.add(thread);
    }

    static class StatusDemoThread extends Thread {
        public StatusDemoThread() {
            super("statusPrintThread" + (++threadSeqNumber));
            //将自己加入到全局的静态线程列表
            addStatusThread(this);
        }

        public void run() {
            Print.tco(getName() + ", 状态为" + getState());
            for (int turn = 0; turn < MAX_TURN; turn++) {
                //线程睡眠
                ThreadUtil.sleepMilliSeconds(500);
                //输出所有线程的状态
                printThreadStatus();
            }
            Print.tco(getName() + "- 运行结束.");
        }
    }

    public static void main(String args[]) throws InterruptedException {

        addStatusThread(Thread.currentThread());

        Thread sThread1 = new StatusDemoThread();
        Print.cfo(sThread1.getName() + "- 状态为" + sThread1.getState());
        Thread sThread2 = new StatusDemoThread();
        Print.cfo(sThread2.getName() + "- 状态为" + sThread2.getState());
        Thread sThread3 = new StatusDemoThread();
        Print.cfo(sThread3.getName() + "- 状态为" + sThread3.getState());
        sThread1.start();

        ThreadUtil.sleepMilliSeconds(500);//等待500ms启动第二个线程
        sThread2.start();

        ThreadUtil.sleepMilliSeconds(500);//等待1000ms启动第三个线程
        sThread3.start();

        ThreadUtil.sleepSeconds(100);//睡眠100秒

    }

    @Test
    //让线程处于TIMED_WAITING状态
    public void testTimedWaiting() {
        final Object lock = new Object();
        synchronized (lock) {
            try {
                lock.wait(30 * 1000);
            } catch (InterruptedException e) {
            }
        }
    }

    //让线程处于WAITING状态
    @Test
    public void testWaiting() {
        final Object lock = new Object();
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
            }
        }
    }

    //让线程一直处于BLOCKED
    @Test
    public void testBlocked() {
        final Object lock = new Object();
        new Thread() {
            public void run() {
                synchronized (lock) {
                    System.out.println("i got lock, but don't release");
                    ThreadUtil.sleepMilliSeconds(1000 * 1000);
                }
            }
        }.start();
        ThreadUtil.sleepMilliSeconds(100 * 1000);
        synchronized (lock) {
            ThreadUtil.sleepMilliSeconds(30 * 1000);
        }
    }
}