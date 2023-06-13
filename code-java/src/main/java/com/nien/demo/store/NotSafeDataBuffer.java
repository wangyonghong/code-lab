package com.nien.demo.store;

import com.nien.demo.util.Print;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

//共享数据区，类定义
class NotSafeDataBuffer<T> {
    public static final int MAX_AMOUNT = 10;
    private List<T> dataList = new LinkedList<>();

    //保存数量
    private AtomicInteger amount = new AtomicInteger(0);

    /**
     * 向数据区增加一个元素
     */
    public void add(T element) throws Exception {
        if (amount.get() > MAX_AMOUNT) {
            Print.tcfo("队列已经满了！");
            return;
        }
        dataList.add(element);
        Print.tcfo(element + "");
        amount.incrementAndGet();

        //如果数据不一致，抛出异常
        if (amount.get() != dataList.size()) {
            throw new Exception(amount + "!=" + dataList.size());
        }
    }

    /**
     * 从数据区取出一个元素
     */
    public T fetch() throws Exception {
        if (amount.get() <= 0) {
            Print.tcfo("队列已经空了！");
            return null;
        }
        T element = dataList.remove(0);
        Print.tcfo(element + "");
        amount.decrementAndGet();
        //如果数据不一致，抛出异常
        if (amount.get() != dataList.size()) {
            throw new Exception(amount + "!=" + dataList.size());
        }
        return element;
    }
}