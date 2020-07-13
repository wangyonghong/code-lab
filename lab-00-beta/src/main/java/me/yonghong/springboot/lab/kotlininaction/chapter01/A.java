package me.yonghong.springboot.lab.kotlininaction.chapter01;

public class A implements AInterface {

    public static String format(String str) {
        return str.isEmpty() ? null : str;
    }

    @Override
    public void print(Integer num) {
        System.out.println("Integer: " + num);
    }

    @Override
    public void print(int num) {
        System.out.println("int: " + num);
    }
}
