package me.yonghong.algo.lc.lc1101;

import me.yonghong.algo.Solution;

/**
 * 1134. 阿姆斯特朗数
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode-cn.com/problems/armstrong-number/"></a>
 * @link <a href="https://leetcode.com/problems/armstrong-number/"></a>
 * @since 2021/8/19
 **/
class Lc1134 implements Solution {

    public static void main(String[] args) {
        new Lc1134().test();
    }

    @Override
    public void test() {
        System.out.println(isArmstrong(153));
        System.out.println(isArmstrong(123));
    }

    public boolean isArmstrong(int n) {
        int tmp = n, sum = 0;
        int k = String.valueOf(n).length();
        for (int i = 0; i < k; i++) {
            sum += Math.pow(tmp % 10, k);
            tmp /= 10;
        }
        return sum == n;
    }
}
