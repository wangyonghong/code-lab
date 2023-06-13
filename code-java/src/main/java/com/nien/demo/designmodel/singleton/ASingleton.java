package com.nien.demo.designmodel.singleton;

public class ASingleton {
    private static ASingleton instance;

    // 私有化构造方法
    private ASingleton() {
    }

    static ASingleton getInstance() {
        if (instance == null) //①②③⑤⑦④
        {
            instance = new ASingleton(); //①②③⑤⑦④
        }
        return instance;
    }
}