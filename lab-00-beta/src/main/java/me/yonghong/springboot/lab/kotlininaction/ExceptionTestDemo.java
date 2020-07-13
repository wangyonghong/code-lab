package me.yonghong.springboot.lab.kotlininaction;

import java.io.IOException;

public class ExceptionTestDemo {

    public static void test1() throws IOException {
        ExceptionTest.Companion.test1();
    }

    public static void test2() {
        ExceptionTest.Companion.test2();
    }

    public static void test3() {
        ExceptionTest.Companion.test3();
    }

    public static void main(String[] args) throws IOException {
        try {
            test1();
        } catch (IOException e) {

        }

        try {
            test2();
        } catch (Exception e) {

        }

        try {
            test3();
        } catch (Exception e) {

        }

    }
}
