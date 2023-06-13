package com.nien.demo.coccurent;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.nien.demo.util.Print;
import com.nien.demo.util.ThreadUtil;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by 尼恩 at 疯狂创客圈
 */

public class GuavaFutureDemo {

    public static final int SLEEP_GAP = 3000;

    static class HotWaterJob implements Callable<Boolean> //①
    {
        @Override
        public Boolean call() throws Exception //②
        {
            try {
                Print.tcfo("洗好水壶");
                Print.tcfo("烧开水");

                //线程睡眠一段时间，代表烧水中
                Thread.sleep(SLEEP_GAP);
                Print.tcfo("水开了");

            } catch (InterruptedException e) {
                Print.tcfo(" 发生异常被中断.");
                return false;
            }
            Print.tcfo(" 烧水工作，运行结束.");

            return true;
        }
    }

    static class WashJob implements Callable<Boolean> {

        @Override
        public Boolean call() throws Exception {
            try {
                Print.tcfo("洗茶杯");
                //线程睡眠一段时间，代表清洗中
                Thread.sleep(SLEEP_GAP);
                Print.tcfo("洗完了");

            } catch (InterruptedException e) {
                Print.tcfo(" 清洗工作 发生异常被中断.");
                return false;
            }
            Print.tcfo(" 清洗工作  运行结束.");
            return true;
        }

    }

    //泡茶喝的工作
    static class DrinkJob {

        boolean waterOk = false;
        boolean cupOk = false;

        public void drinkTea() {
            if (waterOk && cupOk) {
                Print.tcfo("泡茶喝，茶喝完");
                this.waterOk = false;
            }

        }
    }


    public static void main(String args[]) {
        Thread.currentThread().setName("泡茶喝线程");

        //新起一个线程，作为泡茶主线程
        DrinkJob drinkJob = new DrinkJob();

        //烧水的业务逻辑
        Callable<Boolean> hotJob = new HotWaterJob();
        //清洗的业务逻辑
        Callable<Boolean> washJob = new WashJob();

        //创建java 线程池
        ExecutorService jPool =
                Executors.newFixedThreadPool(10);

        //包装java线程池，构造guava 线程池
        ListeningExecutorService gPool =
                MoreExecutors.listeningDecorator(jPool);

        //烧水的回调钩子
        FutureCallback<Boolean> hotWaterHook = new FutureCallback<Boolean>() {
            public void onSuccess(Boolean r) {
                if (r) {
                    drinkJob.waterOk = true;
                    //执行回调方法
                    drinkJob.drinkTea();
                }
            }

            public void onFailure(Throwable t) {
                Print.tcfo("烧水失败，没有茶喝了");
            }
        };
        //启动烧水线程
        ListenableFuture<Boolean> hotFuture = gPool.submit(hotJob);
        //设置烧水任务的回调钩子
        Futures.addCallback(hotFuture, hotWaterHook, gPool);

        //启动清洗线程
        ListenableFuture<Boolean> washFuture = gPool.submit(washJob);
        //使用匿名实例，作为清洗之后的回调钩子
        Futures.addCallback(washFuture, new FutureCallback<Boolean>() {
            public void onSuccess(Boolean r) {
                if (r) {
                    drinkJob.cupOk = true;
                    //执行回调方法
                    drinkJob.drinkTea();
                }
            }

            public void onFailure(Throwable t) {
                Print.tcfo("杯子洗不了，没有茶喝了");
            }
        }, gPool);

        Print.tcfo("干点其他事情....");
        ThreadUtil.sleepSeconds(1);
        Print.tcfo("执行完成");
    }


}