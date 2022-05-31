package me.yonghong.algo.lc.lc0301;

import me.yonghong.algo.Solution;

/**
 * 326. 3的幂
 * 326. Power of Three
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/power-of-three/"></a>
 * @link <a href="https://leetcode.com/problems/power-of-three/"></a>
 * @since 2021/09/23
 **/
class Lc0326 implements Solution {

    public static void main(String[] args) {
        new Lc0326().test();
    }

    @Override
    public void test() {
        System.out.println(isPowerOfThree(27));
        System.out.println(isPowerOfThree(9));
        System.out.println(isPowerOfThree(0));
        System.out.println(isPowerOfThree(45));
    }

    public boolean isPowerOfThree(int n) {
        if (n < 1) {
            return false;
        }
        while (n > 1 && n % 3 == 0) {
            n = n / 3;
        }
        return n == 1;
    }
}
