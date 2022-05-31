package me.yonghong.algo.lc.lc0201;

import me.yonghong.algo.Solution;

/**
 * 263. 丑数
 * 263. Ugly Number
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/ugly-number/"></a>
 * @link <a href="https://leetcode.com/problems/ugly-number/"></a>
 * @see me.yonghong.algo.lc.lc0201.Lc0264
 * @see me.yonghong.algo.lc.lc0301.Lc0313
 * @since 2021/8/9
 */
class Lc0263 implements Solution {

    public static void main(String[] args) {
        new Lc0263().test();
    }

    @Override
    public void test() {
        System.out.println(isUgly(6));
        System.out.println(isUgly(8));
        System.out.println(isUgly(14));
        System.out.println(isUgly(1));
        System.out.println(isUgly(-1));
    }

    public boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }
        while (n % 2 == 0) {
            n = n / 2;
        }
        while (n % 3 == 0) {
            n = n / 3;
        }
        while (n % 5 == 0) {
            n = n / 5;
        }
        return n == 1;
    }
}
