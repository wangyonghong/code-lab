package me.yonghong;


/**
 * @author yonghongwang#163.com
 * @since 2020/12/9
 **/
public class InstanceofTest {

    public static void test1(Object obj) {
        if (obj instanceof String) {
            String str = (String) obj;
            System.out.println(str.contains("Java"));
        } else {
            System.out.println("Not String Type");
        }
    }

    public static void test2(Object obj) {
        if (obj instanceof String str) {
            System.out.println(str.contains("Java"));
        } else {
            System.out.println("Not String Type");
        }
    }

    public static void main(String[] args) {
        test1("Hello, Java");
        test2("Hello, Java");
        test1(null);
        test2(null);
    }
}