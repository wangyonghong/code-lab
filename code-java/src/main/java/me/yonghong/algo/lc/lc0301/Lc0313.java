package me.yonghong.algo.lc.lc0301;

import me.yonghong.algo.Solution;

/**
 * 313. 超级丑数
 * 313. Super Ugly Number
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/super-ugly-number/"></a>
 * @link <a href="https://leetcode.com/problems/super-ugly-number/"></a>
 * @see me.yonghong.algo.lc.lc0201.Lc0263
 * @see me.yonghong.algo.lc.lc0201.Lc0264
 * @since 2021/8/9
 */
class Lc0313 implements Solution {

    public static void main(String[] args) {
        new Lc0313().test();
    }

    @Override
    public void test() {
        System.out.println(nthSuperUglyNumber(12, new int[]{2, 7, 13, 19}));
        System.out.println(nthSuperUglyNumber(1, new int[]{2, 3, 5}));
    }

    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] dp = new int[n];
        dp[0] = 1;
        int[] p = new int[primes.length];
        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            int[] nums = new int[primes.length];
            for (int j = 0; j < primes.length; j++) {
                nums[j] = dp[p[j]] * primes[j];
                min = Math.min(min, nums[j]);
            }
            dp[i] = min;
            for (int j = 0; j < primes.length; j++) {
                if (min == nums[j]) {
                    p[j]++;
                }
            }
        }
        return dp[n - 1];
    }
}
