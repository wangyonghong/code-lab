package com.nien.demo.visiable;

import com.nien.demo.util.Print;

public class StartExample {
    private int x = 0;
    private int y = 1;
    private boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        Thread.currentThread().setName("线程A");
        StartExample startExample = new StartExample();

        Thread threadB = new Thread(startExample::writer, "线程B");
        //线程B启动前，线程A进行了多个内存操作
        Print.tcfo("开始赋值操作");
        startExample.x = 10;
        startExample.y = 20;
        startExample.flag = true;

        threadB.start(); //启动线程B
        Print.tcfo("线程结束");
    }

    public void writer() {
        Print.tcfo("x:" + x);
        Print.tcfo("y:" + y);
        Print.tcfo("flag:" + flag);
    }
}
