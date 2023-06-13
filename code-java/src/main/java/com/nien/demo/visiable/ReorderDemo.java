package com.nien.demo.visiable;

class ReorderDemo {
    int value = 10;
    boolean flag = false;
    int doubleValue = 0;

    public void write() {
        value = 100;      //①
        flag = true;    //②
    }

    public void changeValue() {
        if (flag)    //③
        {
            doubleValue = value + value;//④
        }
    }

    public static void main(String[] args)
            throws InterruptedException {
        int i = 0;
        for (; ; ) {
            i++;

            ReorderDemo reorderDemo = new ReorderDemo();
            Thread one = new Thread(new Runnable() {
                public void run() {
                    reorderDemo.write();
                }
            });
            Thread two = new Thread(new Runnable() {
                public void run() {
                    reorderDemo.changeValue();
                }
            });
            one.start();
            two.start();
            one.join();
            two.join();

            if (reorderDemo.doubleValue == 20) {
                String result = "第" + i + "次操作发生了指令重排";
                System.err.println(result);
            }
        }
    }
}
