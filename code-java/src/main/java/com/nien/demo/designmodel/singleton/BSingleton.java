package com.nien.demo.designmodel.singleton;

public class BSingleton {
    private static BSingleton instance;

    // 私有化构造方法
    private BSingleton() {
    }

    static synchronized BSingleton getInstance() {
        if (instance == null) //①②③⑤⑦④
        {
            instance = new BSingleton();  //①②③⑤⑦④
        }
        return instance;
    }
}