package com.nien.demo.store;

import com.nien.demo.petstore.goods.Goods;
import com.nien.demo.petstore.goods.IGoods;
import com.nien.demo.util.Print;

import java.util.ArrayList;

/**
 * Created by 尼恩@疯狂创客圈.
 */
public class PetStore {
    public static final int CONSUME_GAP = 1000;
    public static final int PRODUCE_GAP = 1000;      //保存数据

    private static PetStore instance = new PetStore();

    private PetStore() {
    }

    public static PetStore inst() {
        return instance;
    }


    private ArrayList<IGoods> goodsList = new ArrayList<IGoods>();


    public void consume() {
        Print.cfo("goodsList.size=" + goodsList.size());
        IGoods goods = goodsList.get(0);
        if (goods == null) {
            Print.cfo("队列已经空了！");
            return;
        }
        Print.cfo(goods + "");
        goodsList.remove(goods);
    }

    public void produce() {
        IGoods goods = Goods.produceOne();
        goodsList.add(goods);
        Print.cfo(goods + "");
    }

    public static void main(String[] args) {

        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        producer.start();
        consumer.start();

    }


    static class Producer extends Thread {
        int turn = 0;

        public Producer() {
            super("生产者");
        }

        @Override
        public void run() {
            while (true) {
                ++turn;
                try {

                    Thread.sleep(CONSUME_GAP);
                    Print.hint(super.getName() + "第" + turn + "轮生产！");
                    PetStore.inst().produce();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    static class Consumer extends Thread {
        int turn = 0;

        public Consumer() {
            super("消费者");
        }

        @Override
        public void run() {
            while (true) {
                ++turn;
                try {
                    Thread.sleep(PRODUCE_GAP);
                    Print.hint(super.getName() + "第" + turn + "轮消费！");
                    PetStore.inst().consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IndexOutOfBoundsException e) {
                    Print.cfo("队列是空的！");
                    e.printStackTrace();
                }
            }
        }


    }
}

