package com.nien.demo.basic.threadlocal;

import com.nien.demo.util.ThreadUtil;

public class SimpleDemo {
    static class DemoClass {
        private ThreadLocal<Integer> i = new ThreadLocal<Integer>() {
            Integer integer;

            @Override
            protected Integer initialValue() {
                return 0;
            }
        };

        public Integer get() {
            return i.get();
        }

        public void set(Integer integer) {
            i.set(i.get() + integer);
        }
    }

    public static void main(String[] args) {
        DemoClass demoClass = new DemoClass();
        for (int i = 1; i <= 100; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 100; j++) demoClass.set(j);
                System.out.println("demoClass.get() = " + demoClass.get());
            }).start();
        }
        ThreadUtil.sleepSeconds(Integer.MAX_VALUE);
    }
}
