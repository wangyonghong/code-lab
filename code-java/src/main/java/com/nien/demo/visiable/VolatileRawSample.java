package com.nien.demo.visiable;

class VolatileRawSample {
    int a = 0;
    boolean flag = false;

    public void write() {
        a = 1;           //①②③⑤⑦④
        flag = true;     //①②③⑤⑦④
    }

    public void read() {
        if (flag) {       //①②③⑤⑦④
            int b = a + 1;    //①②③⑤⑦④
            // do sth.
        }
    }
}