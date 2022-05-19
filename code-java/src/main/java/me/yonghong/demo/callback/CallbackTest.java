package me.yonghong.demo.callback;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class CallbackTest {

    public static void main(String[] args) {
        System.out.println(new CallbackTest().func1());
    }

    public int func1() {
        AtomicInteger res = new AtomicInteger();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        CheckUtils.check(1, new CheckCallback() {
            @Override
            public void onSuccess(int result) {
                res.set(result);
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return res.get();
    }

    private static class CheckUtils {
        public static void check(int input, CheckCallback callback) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    callback.onSuccess(10);
                }
            }.start();
        }
    }

    interface CheckCallback {
        void onSuccess(int result);
    }
}


