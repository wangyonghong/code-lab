package com.nien.demo.basic.create;

import com.nien.demo.util.Print;
import com.nien.demo.util.ThreadUtil;

/**
 * Created by 尼恩@疯狂创客圈 on 2017/2/22.
 */

public class EmptyThreadDemo {

    public static void main(String args[]) throws InterruptedException {
        //使用Thread类创建和启动线程
        Thread thread = new Thread();
        Print.cfo("线程名称：" + thread.getName());
        Print.cfo("线程ID：" + thread.getId());
        Print.cfo("线程状态：" + thread.getState());
        Print.cfo("线程优先级：" + thread.getPriority());
        thread.start();
        Print.cfo("线程状态：" + thread.getState());

        ThreadUtil.sleepMilliSeconds(10);
    }
}