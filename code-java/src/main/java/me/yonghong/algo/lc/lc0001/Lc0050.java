package me.yonghong.algo.lc.lc0001;

import me.yonghong.algo.Solution;

/**
 * 50. Pow(x, n)
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/powx-n/"></a>
 * @link <a href="https://leetcode.com/problems/powx-n/"></a>
 * @see me.yonghong.algo.lc.lc0301.Lc0372
 * @since 2021/8/10
 **/
class Lc0050 implements Solution {

    public static void main(String[] args) {
        new Lc0050().test();
    }

    @Override
    public void test() {
        System.out.println(myPow(2.00000, 10));
        System.out.println(myPow(2.10000, 3));
        System.out.println(myPow(2.00000, -2));
        System.out.println(myPow(2.00000, -2147483648));
    }

    public double myPow(double x, int n) {
        if (n == Integer.MIN_VALUE) {
            return (x == 1 || x == -1) ? 1 : 0;
        } else if (n == 0) {
            return 1;
        }

        if (n < 0) {
            return myPow(1 / x, -n);
        }

        // 2^5 = 2 * 2^4
        // 2^4 = 2^2 * 2^2
        // 2^2 = 2^1 * 2^1
        // 2^1 = 2 * 2^0
        if (n % 2 == 0) {
            return myPow(x * x, n / 2);
        } else {
            return myPow(x, n - 1) * x;
        }
    }

    public double myPow2(double x, int n) {
        if (n == Integer.MIN_VALUE) {
            return (x == 1 || x == -1) ? 1 : 0;
        } else if (n == 0) {
            return 1;
        }
        return n >= 0 ? quickMul(x, n) : 1.0 / quickMul(x, -n);
    }

    public double quickMul(double x, long n) {
        double ans = 1.0;
        // 贡献的初始值为 x
        double x_contribute = x;
        // 在对 N 进行二进制拆分的同时计算答案
        while (n > 0) {
            if (n % 2 == 1) {
                // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                ans *= x_contribute;
            }
            // 将贡献不断地平方
            x_contribute *= x_contribute;
            // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
            n /= 2;
        }
        return ans;
    }
}
