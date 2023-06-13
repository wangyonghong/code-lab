package com.nien.demo.lockfree;

import com.nien.demo.util.BeanUtil;
import com.nien.demo.util.Print;
import com.nien.demo.util.ThreadUtil;
import org.junit.Test;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.lang.Thread.currentThread;

public class CopyOnWriteArrayListTest {
    //并发操作的任务目标
    public static class CocurrentTarget implements Runnable {
        //并发操作的目标队列
        List<String> targetList = null;

        public CocurrentTarget(List<String> targetList) {
            this.targetList = targetList;
        }

        @Override
        public void run() {
            Iterator<String> iterator = targetList.iterator();
            //迭代操作
            while (iterator.hasNext()) {
                // 在迭代操作时，进行列表的修改
                String threadName = currentThread().getName();
                Print.tco("开始往同步队列加入线程名称：" + threadName);
                targetList.add(threadName);
            }
        }
    }

    //测试同步队列：在迭代操作时，进行列表的修改
    @Test
    public void testSynchronizedList() {
        List<String> notSafeList = BeanUtil.asList("a", "b", "c");
        List<String> synList = Collections.synchronizedList(notSafeList);

        CocurrentTarget synchronizedListListDemo = new CocurrentTarget(synList);
        //10个线程并发
        for (int i = 0; i < 10; i++) {
            new Thread(synchronizedListListDemo, "线程" + i).start();
        }
        //主线程等待
        ThreadUtil.sleepSeconds(1000);
    }

    //测试CopyOnWriteArrayList
    @Test
    public void testcopyOnWriteArrayList() {
        List<String> notSafeList = BeanUtil.asList("a", "b", "c");

        //创建一个  CopyOnWriteArrayList 队列
        List<String> copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.addAll(notSafeList);

        //并发操作目标
        CocurrentTarget copyOnWriteArrayListDemo = new CocurrentTarget(copyOnWriteArrayList);
        for (int i = 0; i < 10; i++) {
            new Thread(copyOnWriteArrayListDemo, "线程" + i).start();
        }
        //主线程等待
        ThreadUtil.sleepSeconds(1000);
    }
}
