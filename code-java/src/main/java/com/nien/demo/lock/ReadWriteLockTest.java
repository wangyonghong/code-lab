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
public class ReadWriteLockTest {
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
//            Print.tco("write操作执行完毕");
            return put;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
//            Print.tco("read操作执行完毕");
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

        //创建4条读线程
        for (int i = 0; i < 4; i++) {
            new Thread(readTarget, "读线程" + i).start();
        }
        //创建2条写线程，并启动
        for (int i = 0; i < 2; i++) {
            new Thread(writeTarget, "写线程" + i).start();
        }
    }
}