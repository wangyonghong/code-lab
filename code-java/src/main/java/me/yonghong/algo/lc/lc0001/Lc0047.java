package me.yonghong.algo.lc.lc0001;

import me.yonghong.algo.Solution;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * 47. 全排列 II
 * 47. Permutations II
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode-cn.com/problems/permutations-ii/"></a>
 * @link <a href="https://leetcode.com/problems/permutations-ii/"></a>
 * @since 2021/8/2
 **/
public class Lc0047 implements Solution {

    public static void main(String[] args) {
        new Lc0047().test();
    }

    @Override
    public void test() {
        List<List<Integer>> res = permuteUnique(new int[]{1, 1, 3, 4});
        printListList(res);
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        // 排序
        Arrays.sort(nums);
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
            // 剪枝
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
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
