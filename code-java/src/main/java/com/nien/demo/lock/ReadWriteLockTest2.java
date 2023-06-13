package com.nien.demo.lock;

import com.nien.demo.util.DateUtil;
import com.nien.demo.util.Print;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by 尼恩@疯狂创客圈.
 */
public class ReadWriteLockTest2 {
    //创建一个集合
    final static Map<String, String> MAP = new HashMap<String, String>();
    //创建一个读写锁
    final static ReentrantReadWriteLock LOCK = new ReentrantReadWriteLock();
    //获取读锁
    final static Lock READ_LOCK = LOCK.readLock();
    //获取写锁
    final static Lock WRITE_LOCK = LOCK.writeLock();

    //写操作
    public static Object put(String key, String value) {
        WRITE_LOCK.lock();
        try {
            Print.tco(DateUtil.getNowTime() + " 抢占了WRITE_LOCK，开始执行write操作");
            Thread.sleep(1000);
            String put = MAP.put(key, value);
            Print.tco(Thread.currentThread().getName() + "尝试降级写锁为读锁");
            //写锁降级为读锁（成功）
            READ_LOCK.lock();
            Print.tco(Thread.currentThread().getName() + "写锁降级为读锁成功");


            return put;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            READ_LOCK.unlock();
            WRITE_LOCK.unlock();
        }
        return null;

    }

    //写操作
    public static Object get(String key) {
        READ_LOCK.lock();
        try {
            Print.tco(DateUtil.getNowTime() + " 抢占了READ_LOCK，开始执行read操作");
            Thread.sleep(1000);
            String value = MAP.get(key);
            Print.tco(Thread.currentThread().getName() + "尝试升级读锁为写锁");
            //读锁升级为写锁(失败)
            WRITE_LOCK.lock();
            Print.tco(Thread.currentThread().getName() + "读锁升级为写锁成功");

            return value;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            READ_LOCK.unlock();
        }
        return null;

    }

    public static void main(String[] args) {
        //创建Runnable可执行实例
        Runnable writeTarget = () -> put("key", "value");
        Runnable readTarget = () -> get("key");
        //创建1条写线程，并启动

        new Thread(writeTarget, "写线程").start();

        //创建1条读线程

        new Thread(readTarget, "读线程").start();


    }
}