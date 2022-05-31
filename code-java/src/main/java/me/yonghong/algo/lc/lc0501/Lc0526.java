package me.yonghong.algo.lc.lc0501;

import me.yonghong.algo.Solution;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 526. 优美的排列
 * 526. Beautiful Arrangement
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/beautiful-arrangement/"></a>
 * @link <a href="https://leetcode.com/problems/beautiful-arrangement/"></a>
 * @since 2021/8/16
 **/
class Lc0526 implements Solution {

    int count = 0;

    public static void main(String[] args) {
        new Lc0526().test();
    }

    @Override
    public void test() {
        System.out.println(countArrangement(2));
    }

    public int countArrangement(int n) {
        boolean[] used = new boolean[n];
        Deque<Integer> stack = new ArrayDeque<>();
        dfs(used, stack, n);
        return count;
    }

    private void dfs(boolean[] used, Deque<Integer> stack, int n) {
        if (stack.size() == n) {
            count++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if (used[i]) {
                continue;
            }
            if ((i + 1) % (stack.size() + 1) != 0 && (stack.size() + 1) % (i + 1) != 0) {
                continue;
            }
            stack.push(i + 1);
            used[i] = true;
            dfs(used, stack, n);
            stack.pop();
            used[i] = false;
        }
    }
}
