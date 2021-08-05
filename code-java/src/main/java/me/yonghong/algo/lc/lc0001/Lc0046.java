package me.yonghong.algo.lc.lc0001;

import me.yonghong.algo.Solution;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 46. 全排列
 * 46. Permutations
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode-cn.com/problems/permutations/"></a>
 * @link <a href="https://leetcode.com/problems/permutations/"></a>
 * @since 2021/8/2
 **/
public class Lc0046 implements Solution {

    public static void main(String[] args) {
        new Lc0046().test();
    }

    @Override
    public void test() {
        List<List<Integer>> res = permute(new int[]{1, 2, 3, 4});
        printListList(res);
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // 用 Deque 而不是用 Stack 是 Java 的建议
        Deque<Integer> path = new ArrayDeque<>();
        boolean[] used = new boolean[nums.length];
        dfs(nums, used, path, res);
        return res;
    }

    private void dfs(int[] nums, boolean[] used, Deque<Integer> path, List<List<Integer>> res) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            path.addLast(nums[i]);
            used[i] = true;
            dfs(nums, used, path, res);
            path.removeLast();
            used[i] = false;
        }
    }
}
