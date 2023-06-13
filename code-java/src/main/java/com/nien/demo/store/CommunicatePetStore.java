package com.nien.demo.store;

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

/**
 * Created by 尼恩@疯狂创客圈.
 */
public class CommunicatePetStore {

    public static final int MAX_AMOUNT = 10; //数据区长度


    //共享数据区，类定义
    static class DateBuffer<T> {
        //保存数据
        private List<T> dataList = new LinkedList<>();
        //保存数量
        private volatile int amount = 0;

        private final Object LOCK_OBJECT = new Object();
        private final Object NOT_FULL = new Object();
        private final Object NOT_EMPTY = new Object();

        // 向数据区增加一个元素
        public void add(T element) throws Exception {
            synchronized (NOT_FULL) {
                while (amount >= MAX_AMOUNT) {
                    Print.tcfo("队列已经满了！");
                    //等待未满通知
                    NOT_FULL.wait();
                }
            }
            synchronized (LOCK_OBJECT) {

                if (amount < MAX_AMOUNT) { // 加上双重检查，模拟双检锁在单例模式中应用
                    dataList.add(element);
                    amount++;
                }
            }
            synchronized (NOT_EMPTY) {
                //发送未空通知
                NOT_EMPTY.notify();
            }


        }

        /**
         * 从数据区取出一个商品
         */
        public T fetch() throws Exception {
            synchronized (NOT_EMPTY) {
                while (amount <= 0) {
                    Print.tcfo("队列已经空了！");
                    //等待未空通知
                    NOT_EMPTY.wait();
                }
            }

            T element = null;
            synchronized (LOCK_OBJECT) {
                if (amount > 0) {  // 加上双重检查，模拟双检锁在单例模式中应用
                    element = dataList.remove(0);
                    amount--;
                }
            }

            synchronized (NOT_FULL) {
                //发送未满通知
                NOT_FULL.notify();
            }
            return element;
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

    }

}

