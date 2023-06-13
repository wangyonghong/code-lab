package com.nien.demo.completableFutureDemo;

import com.nien.demo.util.Print;
import com.nien.demo.util.ThreadUtil;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;


public class IntegrityDemo {
    /**
     * 模拟模拟RPC调用1
     */
    public String rpc1() {
        //睡眠400ms,模拟执行耗时
        ThreadUtil.sleepMilliSeconds(600);
        Print.tcfo("模拟RPC调用：服务器 server 1");
        return "sth. from server 1";
    }

    /**
     * 模拟模拟RPC调用2
     */
    public String rpc2() {
        //睡眠400ms,模拟执行耗时
        ThreadUtil.sleepMilliSeconds(600);
        Print.tcfo("模拟RPC调用：服务器 server 2");
        return "sth. from server 2";
    }

    @Test
    public void rpcDemo() throws Exception {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() ->
        {
            return rpc1();
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> rpc2());
        CompletableFuture<String> future3 = future1.thenCombine(future2,
                (out1, out2) ->
                {
                    return out1 + " & " + out2;
                });
        String result = future3.get();
        Print.tco("客户端合并最终的结果：" + result);
    }

    @Test
    public void rxJavaDemo() throws Exception {
        Observable<String> observable1 = Observable.fromCallable(() ->
        {
            return rpc1();
        }).subscribeOn(Schedulers.newThread());
        Observable<String> observable2 = Observable
                .fromCallable(() -> rpc2()).subscribeOn(Schedulers.newThread());

        Observable.merge(observable1, observable2)
                .observeOn(Schedulers.newThread())
                .toList()
                .subscribe((result) -> Print.tco("客户端合并最终的结果：" + result));

        ThreadUtil.sleepSeconds(Integer.MAX_VALUE);
    }

}
