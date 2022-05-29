package me.yonghong.algo.lc.lc0101;

import me.yonghong.algo.Solution;

/**
 * 190. 颠倒二进制位
 * 190. Reverse Bits
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/reverse-bits/"></a>
 * @link <a href="https://leetcode.com/problems/reverse-bits/"></a>
 * @since 2021/8/20
 **/
class Lc0190 extends Solution {

    public static void main(String[] args) {
        new Lc0190().test();
    }

    @Override
    public void test() {
        System.out.println(reverseBits(43261596));
    }

    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32 && n != 0; i++) {
            res |= (n & 1) << (31 - i);
            n >>>= 1;
        }
        return res;
    }
}
