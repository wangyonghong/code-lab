package com.nien.demo.store;

import com.nien.demo.petstore.goods.Goods;
import com.nien.demo.petstore.goods.IGoods;
import com.nien.demo.util.Print;

import java.util.ArrayList;

/**
 * Created by 尼恩@疯狂创客圈.
 */
public class PetStore2 {
    public static final int CONSUME_GAP = 1000;
    public static final int PRODUCE_GAP = 1000;

    private static PetStore2 instance = new PetStore2();

    private PetStore2() {
    }

    public static PetStore2 inst() {
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
        Print.cfo("goodsList.size=" + goodsList.size());

        IGoods goods = Goods.produceOne();
        goodsList.add(goods);
        Print.cfo(goods + "");
    }

    public static void main(String[] args) {

        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        new Thread(producer).start();
        new Thread(consumer).start();


    }


    static class Producer implements Runnable {
        int turn = 0;


        @Override
        public void run() {
            while (true) {
                ++turn;
                try {

                    Thread.sleep(PRODUCE_GAP);
                    Print.hint(Thread.currentThread().getName() + "第" + turn + "轮生产！");

                    PetStore2.inst().produce();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    static class Consumer implements Runnable {
        int turn = 0;


        @Override
        public void run() {
            while (true) {
                ++turn;
                try {
                    Thread.sleep(CONSUME_GAP);
                    Print.hint(Thread.currentThread().getName() + "第" + turn + "轮消费！");
                    PetStore2.inst().consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IndexOutOfBoundsException e) {
                    Print.cfo("队列已经空了！");
//                    e.printStackTrace();
                }
            }
        }


    }

}