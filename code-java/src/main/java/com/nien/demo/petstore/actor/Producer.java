package com.nien.demo.petstore.actor;

import com.nien.demo.util.Print;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

import static com.nien.demo.util.ThreadUtil.sleepMilliSeconds;

/**
 * 生产者任务的定义
 * Created by 尼恩@疯狂创客圈.
 */
public class Producer implements Runnable {
    //生产的时间间隔，产一次等待的时间，默认为200ms
    public static final int PRODUCE_GAP = 200;

    //总次数
    static final AtomicInteger TURN = new AtomicInteger(0);

    //生产者对象编号
    static final AtomicInteger PRODUCER_NO = new AtomicInteger(1);

    //生产者名称
    String name = null;

    //生产的动作
    Callable action = null;

    int gap = PRODUCE_GAP;

    public Producer(Callable action, int gap) {
        this.action = action;
        this.gap = gap;
        if (this.gap <= 0) {
            this.gap = PRODUCE_GAP;
        }
        name = "生产者-" + PRODUCER_NO.incrementAndGet();

    }

    public Producer(Callable action) {
        this.action = action;
        this.gap = PRODUCE_GAP;
        name = "生产者-" + PRODUCER_NO.incrementAndGet();

    }

    @Override
    public void run() {
        while (true) {

            try {
                //执行生产动作
                Object out = action.call();
                //输出生产的结果
                if (null != out) {
                    Print.tcfo("第" + TURN.get() + "轮生产：" + out);
                }
                //每一轮生产之后，稍微等待一下
                sleepMilliSeconds(gap);

                //增加生产轮次
                TURN.incrementAndGet();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}