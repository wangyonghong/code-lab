package com.nien.demo.plus;

/**
 * Created by 尼恩@疯狂创客圈.
 */
public class SafeStaticMethodPlus {
    private static Integer amount = 0;

    public static synchronized void selfPlus() {

        amount++;

    }

    public Integer getAmount() {
        return amount;
    }


}

