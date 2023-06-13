package com.nien.demo.visiable;

public class InstructionReorder {
    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (; ; ) {
            i++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            Thread one = new Thread(new Runnable() {
                public void run() {
                    a = 1;   //①
                    x = b;    //②
                }
            });

            Thread other = new Thread(new Runnable() {
                public void run() {
                    b = 1;  //③
                    y = a;  //④
                }
            });
            one.start();
            other.start();
            one.join();
            other.join();
            String result = "第" + i + "次 (" + x + "," + y + "）";
            if (x == 0 && y == 0) {
                System.err.println(result);
            }
        }
    }
}