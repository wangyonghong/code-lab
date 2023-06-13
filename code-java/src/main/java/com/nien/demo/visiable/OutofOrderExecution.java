package com.nien.demo.visiable;

import com.nien.demo.util.Print;

public class OutofOrderExecution {
    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args)
            throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                a = 1;
                x = b;
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                b = 1;
                y = a;
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        Print.tco("x=" + x + " y=" + y);
    }
}