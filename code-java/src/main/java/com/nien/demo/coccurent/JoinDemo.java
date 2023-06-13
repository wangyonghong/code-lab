package com.nien.demo.coccurent;


import com.nien.demo.util.Print;

/**
 * Created by 尼恩 at 疯狂创客圈
 */

public class JoinDemo {

    public static final int SLEEP_GAP = 500;


    public static String getCurThreadName() {
        return Thread.currentThread().getName();
    }

    static class HotWaterThread extends Thread {


        public HotWaterThread() {
            super("** 烧水-Thread");
        }

        public void run() {

            try {
                Print.tcfo("洗好水壶");
                Print.tcfo("灌上凉水");
                Print.tcfo("放在火上");

                //线程睡眠一段时间，代表烧水中
                Thread.sleep(SLEEP_GAP);
                Print.tcfo("水开了");

            } catch (InterruptedException e) {
                Print.tcfo(" 发生异常被中断.");
            }
            Print.tcfo(" 运行结束.");
        }

    }

    static class WashThread extends Thread {


        public WashThread() {
            super("$$ 清洗-Thread");
        }

        public void run() {

            try {
                Print.tcfo("洗茶壶");
                Print.tcfo("洗茶杯");
                Print.tcfo("拿茶叶");
                //线程睡眠一段时间，代表清洗中
                Thread.sleep(SLEEP_GAP);
                Print.tcfo("洗完了");

            } catch (InterruptedException e) {
                Print.tcfo(" 发生异常被中断.");
            }
            Print.tcfo(" 运行结束.");
        }

    }


    public static void main(String args[]) {

        Thread hThread = new HotWaterThread();
        Thread wThread = new WashThread();

        hThread.start();
        wThread.start();
        try {
            // 合并烧水-线程
            hThread.join();
            // 合并清洗-线程
            wThread.join();

            Thread.currentThread().setName("主线程");
            Print.tcfo("泡茶喝");

        } catch (InterruptedException e) {
            Print.tcfo(getCurThreadName() + "发生异常被中断.");
        }
        Print.tcfo(getCurThreadName() + " 运行结束.");
    }
}