package com.nien.demo.completableFutureDemo;


import com.nien.demo.util.Print;
import com.nien.demo.util.ThreadUtil;

import java.util.concurrent.CompletableFuture;


public class DrinkTea {
    private static final int SLEEP_GAP = 3;//等待3秒

    public static void main(String[] args) {
        // 任务 1：洗水壶 -> 烧开水
        CompletableFuture<Boolean> hotJob =
                CompletableFuture.supplyAsync(() ->
                {
                    Print.tcfo("洗好水壶");
                    Print.tcfo("烧开水");

                    //线程睡眠一段时间，代表烧水中
                    ThreadUtil.sleepSeconds(SLEEP_GAP);
                    Print.tcfo("水开了");
                    return true;

                });

        // 任务 2：洗茶壶 -> 洗茶杯 -> 拿茶叶
        CompletableFuture<Boolean> washJob =
                CompletableFuture.supplyAsync(() ->
                {
                    Print.tcfo("洗茶杯");
                    //线程睡眠一段时间，代表清洗中
                    ThreadUtil.sleepSeconds(SLEEP_GAP);
                    Print.tcfo("洗完了");

                    return true;
                });

        // 任务 3：任务 1 和任务 2 完成后执行：泡茶
        CompletableFuture<String> drinkJob =
                hotJob.thenCombine(washJob, (hotOk, washOK) ->
                {
                    if (hotOk && washOK) {
                        Print.tcfo("泡茶喝，茶喝完");
                        return "茶喝完了";
                    }
                    return "没有喝到茶";
                });

        // 等待任务 3 执行结果
        Print.tco(drinkJob.join());


    }
}

