package me.yonghong.algo.lc.lc0301;

import me.yonghong.algo.Solution;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 372. 超级次方
 * 372. Super Pow
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/super-pow/"></a>
 * @link <a href="https://leetcode.com/problems/super-pow/"></a>
 * @see me.yonghong.algo.lc.lc0001.Lc0050
 * @since 2021/8/10
 **/
class Lc0372 extends Solution {

    int mod = 1337;

    public static void main(String[] args) {
        new Lc0372().test();
    }

    @Override
    public void test() {
        System.out.println(superPow(2, new int[]{3}));
        System.out.println(superPow(2, new int[]{1, 0}));
        System.out.println(superPow(1, new int[]{4, 3, 3, 8, 5, 2}));
        System.out.println(superPow(2147483647, new int[]{2, 0, 0}));
    }

    public int superPow(int a, int[] b) {
        Deque<Integer> stack = new ArrayDeque<>();
        int n = b.length;
        for (int i = 0; i < n; i++) {
            stack.push(b[i]);
        }
        return superPow(a, stack);
    }

    // 递归实现
    private int superPow(int a, Deque<Integer> stack) {
        if (stack.isEmpty()) {
            return 1;
        }
        int last = stack.pop();
        int part1 = myPow(a, last);
        int part2 = myPow(superPow(a, stack), 10);
        return (part1 * part2) % mod;
    }

    // 取模防止溢出
    private int myPow(int a, int k) {
        int res = 1;
        a %= mod;
        for (int i = 0; i < k; i++) {
            res *= a;
            res %= mod;
        }
        return res;
    }
}
