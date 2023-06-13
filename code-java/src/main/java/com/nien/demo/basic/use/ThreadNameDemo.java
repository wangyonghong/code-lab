package com.nien.demo.basic.use;


import com.nien.demo.util.Print;

import static com.nien.demo.util.ThreadUtil.sleepMilliSeconds;
import static com.nien.demo.util.ThreadUtil.sleepSeconds;

public class ThreadNameDemo {
    private static final int MAX_TURN = 3;

    //异步执行目标类
    static class RunTarget implements Runnable {    // 实现Runnable接口
        public void run() {    // 重新run()方法
            for (int turn = 0; turn < MAX_TURN; turn++) {
                sleepMilliSeconds(500);//线程睡眠
                Print.tco("线程执行轮次:" + turn);
            }
        }
    }

    public static void main(String args[]) {
        RunTarget target = new RunTarget();    // 实例化Runnable异步执行目标类
        new Thread(target).start();        // 系统自动设置线程名称
        new Thread(target).start();        // 系统自动命令线程名称
        new Thread(target).start();        // 系统自动命令线程名称
        new Thread(target, "手动命名线程-A").start();        // 手动设置线程名称
        new Thread(target, "手动命名线程-B").start();        // 手动设置线程名称
        sleepSeconds(Integer.MAX_VALUE); //主线程不能结束
    }
};