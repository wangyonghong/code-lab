package com.nien.demo.basic.use;

import com.nien.demo.util.Print;
import org.junit.Test;

import static com.nien.demo.util.ThreadUtil.sleepMilliSeconds;
import static com.nien.demo.util.ThreadUtil.sleepSeconds;

/**
 * Created by 尼恩@疯狂创客圈.
 */

public class InterruptDemo {

    public static final int SLEEP_GAP = 5000;//睡眠时长
    public static final int MAX_TURN = 50;//睡眠次数

    static class SleepThread extends Thread {
        static int threadSeqNumber = 1;

        public SleepThread() {
            super("sleepThread-" + threadSeqNumber);
            threadSeqNumber++;
        }

        public void run() {
            try {
                Print.tco(getName() + " 进入睡眠.");
                // 线程睡眠一会
                Thread.sleep(SLEEP_GAP);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Print.tco(getName() + " 发生被异常打断.");
                return;
            }
            Print.tco(getName() + " 运行结束.");
        }

    }

    public static void main(String args[]) throws InterruptedException {

        Thread thread1 = new SleepThread();
        thread1.start();
        Thread thread2 = new SleepThread();
        thread2.start();
        sleepSeconds(2);//等待2秒
        thread1.interrupt(); //打断线程1
        sleepSeconds(5);//等待5秒
        thread2.interrupt();  //打断线程2，此时线程2已经终止
        sleepSeconds(1);//等待1秒
        Print.tco("程序运行结束.");
    }

    //测试用例：获取异步调用的结果
    @Test
    public void testInterrupted2() {
        Thread thread = new Thread() {
            public void run() {
                Print.tco("线程启动了");
                //一直循环
                while (true) {
                    Print.tco(isInterrupted());
                    sleepMilliSeconds(SLEEP_GAP);

                    //如果调用 interrupt 为true，退出死循环
                    if (isInterrupted()) {
                        Print.tco("线程结束了");
                        return;
                    }
                }
            }
        };
        thread.start();
        sleepSeconds(2);//等待2秒
        thread.interrupt(); //打断线程1
        sleepSeconds(2);//等待2秒
        thread.interrupt();
    }
}