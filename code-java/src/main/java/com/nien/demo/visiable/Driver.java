package com.nien.demo.visiable;

import com.nien.demo.util.Print;
import com.nien.demo.util.ThreadUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

class Driver {
    private static final int N = 100; // 乘客数

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch doneSignal = new CountDownLatch(N);
        //取得CPU密集型线程池
        Executor e = ThreadUtil.getCpuIntenseTargetThreadPool();

        for (int i = 1; i <= N; ++i) // 启动报数任务
            e.execute(new Person(doneSignal, i));

        doneSignal.await(); //等待报数完成
        Print.tcfo("人数到期，开车");
    }

    static class Person implements Runnable {
        private final CountDownLatch doneSignal;
        private final int i;

        Person(CountDownLatch doneSignal, int i) {
            this.doneSignal = doneSignal;
            this.i = i;
        }

        public void run() {
            try {
                //报数
                Print.tcfo("第" + i + "个人以到");
                doneSignal.countDown();
            } catch (Exception ex) {
            } // return;
        }


    }
}

