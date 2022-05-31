package me.yonghong.algo.lc.lc0001;

import me.yonghong.algo.Solution;
import me.yonghong.algo.SolutionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和
 * 1. Two Sum
 * 标签：数组、哈希表
 * 难度：简单
 * <p>
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出和为目标值 target 的那两个整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 * <p>
 * 示例 1：
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * <p>
 * 示例 2：
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * <p>
 * 示例 3：
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 * <p>
 * 进阶：你可以想出一个时间复杂度小于 O(n^2) 的算法吗？
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/two-sum/"></a>
 * @link <a href="https://leetcode.com/problems/two-sum/"></a>
 * @see me.yonghong.algo.lc.lc0101.Lc0167
 * @since 2021/2/21
 **/
public interface Lc0001 extends Solution {

    public static void main(String[] args) {
        SolutionUtils.runTest(Lc0001.class);
    }

    int[] twoSum(int[] nums, int target);

    @Override
    default void test() {
        int[] res1 = twoSum(new int[]{2, 7, 11, 15}, 9);
        print(res1);
        int[] res2 = twoSum(new int[]{3, 2, 4}, 6);
        print(res2);
        int[] res3 = twoSum(new int[]{3, 3}, 6);
        print(res3);
    }

    class Solution1 implements Lc0001 {

        @Override
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (map.get(target - nums[i]) != null) {
                    return new int[]{map.get(target - nums[i]), i};
                }
                map.put(nums[i], i);
            }
            return new int[]{};
        }
    }

    class Solution2 implements Lc0001 {

        @Override
        public int[] twoSum(int[] nums, int target) {
            for (int i = 0; i < nums.length - 1; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[i] + nums[j] == target) {
                        return new int[]{i, j};
                    }
                }
            }
            return new int[]{};
        }
    }
}

