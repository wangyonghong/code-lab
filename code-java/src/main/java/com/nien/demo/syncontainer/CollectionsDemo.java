package com.nien.demo.syncontainer;

import com.nien.demo.util.Print;
import com.nien.demo.util.ThreadUtil;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;

public class CollectionsDemo {
    public static void main(String[] args) throws InterruptedException {
        // 创建一下有序集合
        SortedSet<String> elementSet = new TreeSet<String>();

        // 增加元素
        elementSet.add("element 1");
        elementSet.add("element 2");

        // 将 elementSet 包装成一个同步容器
        SortedSet sorset = Collections.synchronizedSortedSet(elementSet);
        //输出容器中的元素
        System.out.println("SortedSet is :" + sorset);
        CountDownLatch latch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            ThreadUtil.getCpuIntenseTargetThreadPool()
                    .submit(() -> {
                        // 向同步容器中增加一个元素
                        sorset.add("element " + (3 + finalI));
                        Print.tco("add element" + (3 + finalI));
                        latch.countDown();
                    });

        }
        latch.await();
        //输出容器中的元素
        System.out.println("SortedSet  is :" + sorset);

    }
}
