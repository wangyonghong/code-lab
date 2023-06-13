package com.nien.demo.basic.threadlocal;

import com.nien.demo.util.Print;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SpeedLogThreadPool extends ThreadPoolExecutor {

    public SpeedLogThreadPool() {
        //模拟 Executors.newCachedThreadPool
        /*        super(0,
                Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());*/
        //模拟 Executors.newSingleThreadExecutor
        super(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }


    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        Print.fo("start task execute.....");
        //开始耗时记录
        SpeedLog.beginSpeedLog();
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        try {
            Print.fo("end task execute!");
            SpeedLog.printCost(); //打印耗时
            SpeedLog.endSpeedLog(); //结束耗时记录
        } finally {
            super.afterExecute(r, t);
        }
    }

    @Override
    protected void terminated() {
        try {
            Print.fo("线程结束!!!!");
        } finally {
            super.terminated();
        }
    }
}