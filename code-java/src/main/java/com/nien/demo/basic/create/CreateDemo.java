package com.nien.demo.basic.create;

import com.nien.demo.util.Print;
import com.nien.demo.util.ThreadUtil;

/**
 * Created by 尼恩@疯狂创客圈.
 */

public class CreateDemo {

    public static final int MAX_TURN = 5;


    static int threadNo = 1;

    static class DemoThread extends Thread {

        public DemoThread() {
            super("Mall-" + threadNo++);
        }

        public void run() {
            for (int i = 1; i < MAX_TURN; i++) {
                Print.cfo(getName() + ", 轮次：" + i);
            }
            Print.cfo(getName() + " 运行结束.");
        }
    }


    public static void main(String args[]) throws InterruptedException {
        Thread thread = null;
        //方法一：使用Thread子类创建和启动线程
        for (int i = 0; i < 2; i++) {
            thread = new DemoThread();
            thread.start();
        }

        Print.cfo(ThreadUtil.getCurThreadName() + " 运行结束.");
    }
}