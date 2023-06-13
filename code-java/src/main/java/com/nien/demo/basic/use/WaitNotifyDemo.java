package com.nien.demo.basic.use;

import com.nien.demo.util.Print;
import com.nien.demo.util.ThreadUtil;

public class WaitNotifyDemo {
    static Object locko = new Object();

    //等待线程
    static class WaitTarget implements Runnable {
        public void run() {
            //加锁
            synchronized (locko) {
                try {
                    //启动等待，同时释放locko监视器的Owner权限
                    Print.tco("启动等待");
                    //等待被通知，同时释放locko监视器的Owner权限
                    locko.wait();
                    //收到通知后，线程会进入locko监视器的EntryList
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //条件满足
                Print.tco("收到通知，当前线程继续执行");
            }
        }
    }

    //通知线程
    static class NotifyTarget implements Runnable {
        public void run() {
            //加锁
            synchronized (locko) {
                //从屏幕读取输入，目的阻塞通知线程，方便使用jstack查看线程状态
                Print.consoleInput();
                //获取lock锁，然后进行发送
                // 此时不会立即释放locko的Monitor的Owner，需要该线程执行完毕
                locko.notifyAll();
                Print.tco("发出通知了，但是线程还没有立马释放锁");
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        //创建等待线程
        Thread waitThread = new Thread(new WaitTarget(), "WaitThread");
        //启动等待线程
        waitThread.start();
        ThreadUtil.sleepSeconds(1);
        //创建通知线程
        Thread notifyThread = new Thread(new NotifyTarget(), "NotifyThread");
        //启动通知线程
        notifyThread.start();
    }

}