package com.nien.demo.visiable;

import com.nien.demo.util.Print;

public class JoinExample {
    private int x = 0;
    private int y = 1;
    private boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        Thread.currentThread().setName("线程A");
        JoinExample joinExample = new JoinExample();

        Thread threadB = new Thread(joinExample::writer, "线程B");
        threadB.start();

        threadB.join();//线程A join线程B

        Print.tcfo("x:" + joinExample.x);
        Print.tcfo("y:" + joinExample.y);
        Print.tcfo("flag:" + joinExample.flag);
        Print.tcfo("本线程结束");
    }

    public void writer() {
        Print.tcfo("开始赋值操作");
        this.x = 100;
        this.y = 200;
        this.flag = true;
    }
}

