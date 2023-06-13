package com.nien.demo.plus;

/**
 * Created by 尼恩@疯狂创客圈.
 */
public class SafePlus {
    private Integer amount = 0;

    public void selfPlus() {
        synchronized (this) {
            amount++;
        }
    }

    public Integer getAmount() {
        return amount;
    }


}

