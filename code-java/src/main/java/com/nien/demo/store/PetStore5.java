package com.nien.demo.store;

import com.nien.demo.petstore.goods.Goods;
import com.nien.demo.petstore.goods.IGoods;
import com.nien.demo.util.Print;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 尼恩@疯狂创客圈.
 */
public class PetStore5 {
    public static final int CONSUME_GAP = 1000;
    public static final int PRODUCE_GAP = 1000;

    private static PetStore5 instance = new PetStore5();
    private final int MAX_AMOUNT = 4;
    private final Object LOCK_PRODUCE = new Object();
    private final Object LOCK_CONSUME = new Object();
    private AtomicInteger amount = new AtomicInteger(0);
    private ArrayList<IGoods> goodsList = new ArrayList<IGoods>();

    private PetStore5() {
    }

    public static PetStore5 inst() {
        return instance;
    }


    public void consume() {

        synchronized (LOCK_CONSUME) {
            Print.cfo("goodsList.size=" + goodsList.size());
            IGoods goods = goodsList.get(0);
            while (goods == null) {
                Print.cfo("队列已经空了！");
                try {
                    LOCK_CONSUME.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Print.cfo(goods + "");
            goodsList.remove(goods);
            amount.decrementAndGet();
        }

        synchronized (LOCK_PRODUCE) {
            LOCK_PRODUCE.notify();
        }


    }


    public void produce() {

        synchronized (LOCK_PRODUCE) {
            while (amount.get() > MAX_AMOUNT) {
                Print.cfo("队列已经满了！");
                try {
                    LOCK_PRODUCE.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            IGoods goods = Goods.produceOne();
            goodsList.add(goods);

            Print.cfo(goods + "");

        }
        synchronized (LOCK_CONSUME) {
            LOCK_CONSUME.notify();
        }
    }


    static class Producer extends Thread {
        static int producerNo = 1;

        public Producer() {
            super("生产者" + producerNo++);
        }

        @Override
        public void run() {
            while (true) {
                Print.hint(super.getName() + "开始生产！");

                PetStore5.inst().produce();


                try {
                    Thread.sleep(PRODUCE_GAP);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    static class Consumer extends Thread {
        static int consumerNO = 1;

        public Consumer() {
            super("消费者" + consumerNO++);
        }

        @Override
        public void run() {
            while (true) {
                Print.hint(super.getName() + "开始消费！");
                try {
                    Thread.sleep(CONSUME_GAP);
                    PetStore5.inst().consume();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IndexOutOfBoundsException e) {
                    Print.cfo("队列已经空了！");
//                    e.printStackTrace();
                }
            }
        }


    }

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            new Producer().start();
            new Consumer().start();
        }


    }

}