package com.nien.demo.visiable;

public class VolatileVar {
    //使用volatile保障内存可见性
    volatile int var = 0;

    public void setVar(int var) {
        System.out.println("setVar = " + var);
        this.var = var;
    }

    public static void main(String[] args) {
        VolatileVar var = new VolatileVar();
        var.setVar(100);
    }
}