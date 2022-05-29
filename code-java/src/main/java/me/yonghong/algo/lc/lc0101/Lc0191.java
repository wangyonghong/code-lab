package me.yonghong.algo.lc.lc0101;

import me.yonghong.algo.Solution;

/**
 * 191. 位1的个数
 * 191. Number of 1 Bits
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/number-of-1-bits/"></a>
 * @link <a href="https://leetcode.com/problems/number-of-1-bits/"></a>
 * @since 2021/8/20
 **/
class Lc0191 extends Solution {

    public static void main(String[] args) {
        new Lc0191().test();
    }

    @Override
    public void test() {
        System.out.println(hammingWeight(3));
        System.out.println(hammingWeight(1));
        System.out.println(hammingWeight(31));
    }

    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            count += n & 1;
            n >>>= 1;
        }
        return count;
    }
}
