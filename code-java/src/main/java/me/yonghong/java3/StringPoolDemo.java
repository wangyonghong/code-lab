package me.yonghong.java3;

/**
 * https://www.bilibili.com/video/BV1Hy4y1B78T?p=2
 *
 * @author yonghongwang#163.com
 * @since 2020/12/10
 **/
public class StringPoolDemo {

    public static void main(String[] args) {
        String str1 = new StringBuilder("计算机").append("基础").toString();
        System.out.println(str1);
        System.out.println(str1.intern());
        System.out.println(str1 == str1.intern());

        System.out.println();

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2);
        System.out.println(str2.intern());
        System.out.println(str2 == str2.intern());
    }
}
