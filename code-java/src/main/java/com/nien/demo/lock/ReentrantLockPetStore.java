package com.nien.demo.lock;

import com.nien.demo.petstore.actor.Consumer;
import com.nien.demo.petstore.actor.Producer;
import com.nien.demo.petstore.goods.Goods;
import com.nien.demo.petstore.goods.IGoods;
import com.nien.demo.util.JvmUtil;
import com.nien.demo.util.Print;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.nien.demo.util.ThreadUtil.sleepSeconds;

/**
 * Created by 尼恩@疯狂创客圈.
 */
public class ReentrantLockPetStore {

    public static final int MAX_AMOUNT = 10; //数据区长度


    //共享数据区，类定义
    static class DateBuffer<T> {
        //保存数据
        private List<T> dataList = new LinkedList<>();
        //保存数量
        private volatile Integer amount = 0;


        public static final Lock LOCK_OBJECT = new ReentrantLock();
        public static final Condition NOT_FULL = LOCK_OBJECT.newCondition();
        public static final Condition NOT_EMPTY = LOCK_OBJECT.newCondition();

        // 向数据区增加一个元素
        public void add(T element) throws Exception {
            LOCK_OBJECT.lock();
            try {
                while (amount > MAX_AMOUNT) {
                    Print.tcfo("队列已经满了！");
                    //等待未满通知
                    NOT_FULL.await();
                }
            } finally {
                LOCK_OBJECT.unlock();
            }


            LOCK_OBJECT.lock();
            try {
                if (amount <= MAX_AMOUNT) {
                    dataList.add(element);
                    amount++;
                    //发送未空通知
                    NOT_EMPTY.signal();
                }
            } finally {
                LOCK_OBJECT.unlock();
            }

        }

        /**
         * 从数据区取出一个商品
         */
        public T fetch() throws Exception {
            LOCK_OBJECT.lock();
            try {
                while (amount <= 0) {
                    Print.tcfo("队列已经空了！");
                    //等待未空通知
                    NOT_EMPTY.await();
                }
            } finally {
                LOCK_OBJECT.unlock();
            }


            T element = null;
            LOCK_OBJECT.lock();
            try {
                if (amount > 0) {
                    element = dataList.remove(0);
                    amount--;

                    //发送未满通知
                    NOT_FULL.signal();
                }
                return element;

            } finally {
                LOCK_OBJECT.unlock();
            }


        }
    }


    public static void main(String[] args) throws InterruptedException {
        Print.cfo("当前进程的ID是" + JvmUtil.getProcessID());
        System.setErr(System.out);
        //共享数据区，实例对象
        DateBuffer<IGoods> dateBuffer = new DateBuffer<>();

        //生产者执行的动作
        Callable<IGoods> produceAction = () ->
        {
            //首先生成一个随机的商品
            IGoods goods = Goods.produceOne();
            //将商品加上共享数据区
            dateBuffer.add(goods);
            return goods;
        };
        //消费者执行的动作
        Callable<IGoods> consumerAction = () ->
        {
            // 从PetStore获取商品
            IGoods goods = null;

            goods = dateBuffer.fetch();
            return goods;
        };
        // 同时并发执行的线程数
        final int THREAD_TOTAL = 20;
        //线程池，用于多线程模拟测试
        ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_TOTAL);

        //假定共11条线程，其中有10个消费者，但是只有1个生产者；
        final int CONSUMER_TOTAL = 11;
        final int PRODUCE_TOTAL = 1;

        for (int i = 0; i < PRODUCE_TOTAL; i++) {
            //生产者线程每生产一个商品，间隔50ms
            threadPool.submit(new Producer(produceAction, 50));
        }
        for (int i = 0; i < CONSUMER_TOTAL; i++) {
            //消费者线程每消费一个商品，间隔100ms
            threadPool.submit(new Consumer(consumerAction, 100));
        }

        sleepSeconds(Integer.MAX_VALUE);


    }

}

