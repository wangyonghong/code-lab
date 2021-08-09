package me.yonghong.algo.lc.lc0201;

import me.yonghong.algo.Solution;

/**
 * 264. 丑数 II
 * 264. Ugly Number II
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode-cn.com/problems/ugly-number-ii/"></a>
 * @link <a href="https://leetcode.com/problems/ugly-number-ii/"></a>
 * @see me.yonghong.algo.lc.lc0201.Lc0263
 * @see me.yonghong.algo.lc.lc0301.Lc0313
 * @since 2021/8/9
 */
public class Lc0264 implements Solution {

    public static void main(String[] args) {
        new Lc0264().test();
    }

    @Override
    public void test() {
        System.out.println(nthUglyNumber(1));
        System.out.println(nthUglyNumber(2));
        System.out.println(nthUglyNumber(3));
        System.out.println(nthUglyNumber(10));
    }

    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int p2 = 0, p3 = 0, p5 = 0;
        for (int i = 1; i < n; i++) {
            int n1 = dp[p2] * 2, n2 = dp[p3] * 3, n3 = dp[p5] * 5;
            dp[i] = Math.min(n1, Math.min(n2, n3));
            if (dp[i] == n1) {
                p2++;
            }
            if (dp[i] == n2) {
                p3++;
            }
            if (dp[i] == n3) {
                p5++;
            }
        }
        return dp[n - 1];
    }
}
