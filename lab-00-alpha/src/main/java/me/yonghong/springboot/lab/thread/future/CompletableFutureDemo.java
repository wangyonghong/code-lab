package me.yonghong.springboot.lab.thread.future;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {


    public static void main(String[] args) {
        CompletableFuture.supplyAsync(System::currentTimeMillis);
    }
}
