package com.nien.demo.designmodel.singleton;

public class ESingleton {
    private static volatile ESingleton instance;

    // 私有化构造方法
    private ESingleton() {
    }

    static ESingleton getInstance() {
        if (instance == null)  //①②③⑤⑦④
        {
            synchronized (ESingleton.class) {
                if (instance == null) //①②③⑤⑦④
                {
                    instance = new ESingleton();
                }
            }
        }
        return instance;
    }


}