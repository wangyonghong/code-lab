package com.nien.demo.lock;

import com.nien.demo.petstore.goods.IGoods;
import com.nien.demo.util.Print;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by 尼恩@疯狂创客圈.
 */
public class PetStoreWithReadWriteLock {
    //数据缓冲区的大小
    public static final int MAX_AMOUNT = 10;

    //共享数据区 受到保存的内部成员
    private ArrayList<IGoods> goodsList = new ArrayList<IGoods>(MAX_AMOUNT);

    //成员：读写锁
    ReentrantReadWriteLock rtLock = new ReentrantReadWriteLock();

    //成员：读锁
    private final Lock readLock = rtLock.readLock();

    //成员：写锁
    private final Lock writeLock = rtLock.writeLock();

    //保存数量
    private AtomicInteger amount = new AtomicInteger(0);

    /**
     * 向数据区增加一个商品
     *
     * @param goods 商品
     * @throws Exception
     */
    public void add(IGoods goods) {
        writeLock.lock();// 抢占写锁
        try {
            if (amount.get() > MAX_AMOUNT) {
                Print.tcfo("队列已经满了！");
                return;
            }
            goodsList.add(goods);
            Print.tcfo(goods + "");
            amount.incrementAndGet();
        } finally {
            writeLock.unlock();// 释放写锁
        }
    }

    /**
     * 从数据区按照条件查找商品list
     *
     * @param predicate 商品查询条件
     * @return 查询的结果集
     */
    public List<IGoods> search(Predicate predicate) {
        int count = 0;
        readLock.lock();// 抢占读锁
        try {
            //eg: Predicate predicate= goods-> example.equals(goods);
            return (List<IGoods>) CollectionUtils.select(goodsList, predicate);
        } finally {
            readLock.unlock();// 释放读锁
        }
    }


    /**
     * 从数据区取出一个商品
     */
    public IGoods fetch() {
        writeLock.lock();// 抢占写锁
        try {
            IGoods goods = null;
            if (amount.get() <= 0) {
                Print.tcfo("队列已经空了！");
                return null;
            }
            goods = goodsList.remove(0);
            Print.tcfo(goods + "");
            amount.decrementAndGet();
            return goods;
        } finally {
            writeLock.unlock();// 释放写锁
        }
    }


}

