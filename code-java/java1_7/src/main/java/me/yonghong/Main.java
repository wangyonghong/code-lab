package me.yonghong;


public class Main {
    public static void main(final String[] args) {
        System.out.println("Hello world!");
        new Runnable() {
            @Override
            public void run() {
                System.out.println(args.length);
            }
        };
    }
}