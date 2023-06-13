package com.nien.demo.designmodel.singleton;

public class FSingleton {
    private static final FSingleton instance = new FSingleton();

    // 私有化构造方法
    private FSingleton() {
    }

    static FSingleton getInstance() {
        return instance;
    }


}