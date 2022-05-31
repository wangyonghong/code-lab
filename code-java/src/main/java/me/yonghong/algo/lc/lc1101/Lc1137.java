package me.yonghong.algo.lc.lc1101;

import me.yonghong.algo.Solution;

/**
 * 1137. 第 N 个泰波那契数
 * 1137. N-th Tribonacci Number
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/n-th-tribonacci-number/"></a>
 * @link <a href="https://leetcode.com/problems/n-th-tribonacci-number/"></a>
 * @since 2021/8/8
 **/
class Lc1137 implements Solution {

    public static void main(String[] args) {
        new Lc1137().test();
    }

    @Override
    public void test() {
        System.out.println(tribonacci(0));
        System.out.println(tribonacci(1));
        System.out.println(tribonacci(2));
        System.out.println(tribonacci(3));
        System.out.println(tribonacci(4));
        System.out.println(tribonacci(5));
        System.out.println(tribonacci(6));
        System.out.println(tribonacci(25));
    }

    public int tribonacci(int n) {
        int t0 = 0, t1 = 1, t2 = 1;
        for (int i = 0; i < n; i++) {
            int temp = t0 + t1 + t2;
            t0 = t1;
            t1 = t2;
            t2 = temp;
        }
        return t0;
    }
}
