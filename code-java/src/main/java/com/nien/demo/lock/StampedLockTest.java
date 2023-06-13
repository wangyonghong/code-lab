package com.nien.demo.lock;

import com.nien.demo.util.Print;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.StampedLock;

import static com.nien.demo.util.DateUtil.getNowTime;
import static com.nien.demo.util.ThreadUtil.sleepSeconds;

/**
 * Created by 尼恩@疯狂创客圈.
 */
public class StampedLockTest {
    //创建一个Map，代表共享数据
    final static Map<String, String> MAP = new HashMap<String, String>();
    //创建一个读写锁
    final static StampedLock STAMPED_LOCK = new StampedLock();

    //写操作
    public static Object put(String key, String value) {
        long stamp = STAMPED_LOCK.writeLock();   //尝试获取写锁
        try {
            Print.tco(getNowTime() + " 抢占了WRITE_LOCK，开始执行write操作");
            Thread.sleep(1000);
            String put = MAP.put(key, value);
            return put;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Print.tco(getNowTime() + " 释放了WRITE_LOCK");
            STAMPED_LOCK.unlockWrite(stamp); //释放写锁
        }
        return null;

    }


    //对共享数据的悲观读操作
    public static Object pessimisticRead(String key) {
        Print.tco(getNowTime() + " LOCK进入过写模式，只能悲观读");
        //写锁已经被抢占，进入了写锁模式，只能获取悲观读锁
        long stamp = STAMPED_LOCK.readLock();
        try {
            Print.tco(getNowTime() + " 抢占了READ_LOCK");
            String value = MAP.get(key); //成功获取到读锁，并重新获取最新的变量值
            return value;
        } finally {
            Print.tco(getNowTime() + " 释放了READ_LOCK");
            STAMPED_LOCK.unlockRead(stamp); //释放读锁
        }
    }


    //对共享数据的乐观读操作
    public static Object optimisticRead(String key) {
        String value = null;
        //尝试进行乐观读
        long stamp = STAMPED_LOCK.tryOptimisticRead();
        if (0 != stamp) {
            Print.tco(getNowTime() + "乐观读的印戳值，获取成功");
            sleepSeconds(1); //模拟耗费时间1秒
            value = MAP.get(key);

        } else  // 0 == stamp
        {
            Print.tco(getNowTime() + "乐观读的印戳值，获取失败");
            //LOCK已经进入写模式
            return pessimisticRead(key);
        }

        //验证乐观读的印戳值是否有效，无效则LOCK进入过写模式
        //乐观读的印戳值无效，表明写锁被占用过
        if (!STAMPED_LOCK.validate(stamp)) {
            Print.tco(getNowTime() + " 乐观读的印戳值，已经过期");
            //写锁已经被抢占，进入了写锁模式，只能获取悲观读锁
            return pessimisticRead(key);
        } else {
            //乐观读的印戳值有，表明写锁没有被占用过
            //如果STAMPED_LOCK写锁没有被抢占，那么可以不用加悲观读锁，减少了读锁的开销
            Print.tco(getNowTime() + " 乐观读的印戳值，没有过期");
            return value;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //创建Runnable可执行实例
        Runnable writeTarget = () -> put("key", "value");
        Runnable readTarget = () -> optimisticRead("key");
        //创建1条写线程，并启动
        new Thread(writeTarget, "写线程").start();
        //创建1条读线程
        new Thread(readTarget, "读线程").start();
    }
}