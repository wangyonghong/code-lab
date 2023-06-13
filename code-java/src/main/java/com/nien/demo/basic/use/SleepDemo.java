package com.nien.demo.basic.use;

import com.nien.demo.util.Logger;
import com.nien.demo.util.Print;
import org.junit.Test;

import static com.nien.demo.util.JvmUtil.getProcessID;
import static com.nien.demo.util.ThreadUtil.getCurThreadName;

/**
 * Created by 尼恩@疯狂创客圈.
 */

public class SleepDemo {

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
                for (int i = 1; i < MAX_TURN; i++) {
                    Print.tco(getName() + ", 睡眠轮次：" + i);
                    // 线程睡眠一会
                    Thread.sleep(SLEEP_GAP);
                }
            } catch (InterruptedException e) {
                Print.tco(getName() + " 发生异常被中断.");

            }
            Print.tco(getName() + " 运行结束.");
        }

    }

    public static void main(String args[]) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            Thread thread = new SleepThread();
            thread.start();
        }
        Print.tco(getCurThreadName() + " 运行结束.");
    }


    @Test
    public void sleepForever() {
        //获取进程id，避免去任务管理器查找
        Logger.cfo("进程ID=" + getProcessID());
        try {
            //main线程，无限制等待
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}