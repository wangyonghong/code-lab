package com.nien.demo.plus;

import com.nien.demo.util.Print;

import java.util.concurrent.CountDownLatch;

/**
 * Created by 尼恩@疯狂创客圈.
 */
public class PlusTest {
    final int MAX_TREAD = 10;
    final int MAX_TURN = 1000;
    CountDownLatch latch = new CountDownLatch(MAX_TREAD);

    /**
     * 测试用例：测试不安全的累加器
     */
    @org.junit.Test
    public void testNotSafePlus() throws InterruptedException {
        NotSafePlus counter = new NotSafePlus();
        Runnable runnable = () ->
        {
            for (int i = 0; i < MAX_TURN; i++) {
                counter.selfPlus();
            }
            latch.countDown();
        };
        for (int i = 0; i < MAX_TREAD; i++) {
            new Thread(runnable).start();
        }
        latch.await();
        Print.tcfo("理论结果：" + MAX_TURN * MAX_TREAD);
        Print.tcfo("实际结果：" + counter.getAmount());
        Print.tcfo("差距是：" + (MAX_TURN * MAX_TREAD - counter.getAmount()));
    }

    /**
     * 测试用例：安全的累加器
     */
    @org.junit.Test
    public void testSafePlus() throws InterruptedException {
        SafePlus counter = new SafePlus();
        Runnable runnable = () ->
        {
            for (int i = 0; i < MAX_TURN; i++) {
                counter.selfPlus();
            }
            latch.countDown();
        };
        for (int i = 0; i < MAX_TREAD; i++) {
            new Thread(runnable).start();
        }
        latch.await();
        Print.tcfo("理论结果：" + MAX_TURN * MAX_TREAD);
        Print.tcfo("实际结果：" + counter.getAmount());
        Print.tcfo("差距是：" + (MAX_TURN * MAX_TREAD - counter.getAmount()));
    }


}

