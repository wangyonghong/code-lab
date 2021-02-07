package me.yonghong.interview;

/**
 * @author yonghongwang#163.com
 * @since 2020/12/17
 **/
public class TestSumN {

    public static void main(String[] args) {
        System.out.println(sum(4));
    }

    public static int sum(int n) {
        boolean b = n > 0 && (n += sum(n - 1)) > 0;
        return n;
    }
}
