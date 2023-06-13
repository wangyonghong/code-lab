package com.nien.demo.basic.use;

import com.nien.demo.util.Print;

/**
 * Created by 尼恩@疯狂创客圈.
 */

public class YieldDemo2 {

    public static final int SLEEP_GAP = 500;


    public static String getCurThreadName() {
        return Thread.currentThread().getName();
    }

    static class JoinDemoThread extends Thread {


        public JoinDemoThread() {
            super("joinThread");
        }

        public void run() {

            try {
                Print.cfo(getName() + ",洗好水壶");
                Print.cfo(getName() + ",灌上凉水");
                Print.cfo(getName() + ",放在火上");
                //线程睡眠一段时间，代表烧水中
                Thread.sleep(SLEEP_GAP);
                Print.cfo(getName() + ",水开了");

            } catch (InterruptedException e) {
                Print.cfo(getName() + " 发生异常被中断.");
            }
            Print.cfo(getName() + " 运行结束.");
        }

    }


    public static void main(String args[]) {

        Thread jThread = new JoinDemoThread();

        jThread.start();
        try {
            Thread.yield();

            Print.cfo(getCurThreadName() + ",洗茶壶");
            Print.cfo(getCurThreadName() + ",洗茶杯");
            Print.cfo(getCurThreadName() + ",拿茶叶");

            jThread.join();
            Print.cfo(getCurThreadName() + ",泡茶喝");

        } catch (InterruptedException e) {
            Print.cfo(getCurThreadName() + "发生异常被中断.");
        }
        Print.cfo(getCurThreadName() + " 运行结束.");
    }
}