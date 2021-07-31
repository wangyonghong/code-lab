package me.yonghong.demo.basic;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author yonghongwang#163.com
 * @since 2021/7/30
 **/
public class CharacterTest {

    public static void main(String[] args) {
        test_1();
    }

    private static void test_1() {
        // char ok = '👌'; 大部分字符可以用 char 存储，但一些特殊字符、表情不可以
        String ok = "👌";
        System.out.println(ok);
        System.out.println(Arrays.toString(ok.getBytes(StandardCharsets.UTF_8)));
        byte[] okArray = new byte[]{-16, -97, -111, -116};
        ok = new String(okArray);
        System.out.println(ok);
    }
}
