package me.yonghong.lc.lc0001;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yonghongwang#163.com
 * @since 2021/2/21
 * @link https://leetcode-cn.com/problems/two-sum/
 *
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 **/
public class Lc0001 {

    public static void main(String[] args) {
        int[] res1 = new Solution().twoSum(new int[]{2, 7, 11, 15}, 9);
        System.out.println(Arrays.toString(res1));
        int[] res2 = new Solution().twoSum(new int[]{3, 2, 4}, 6);
        System.out.println(Arrays.toString(res2));
        int[] res3 = new Solution().twoSum(new int[]{3, 3}, 6);
        System.out.println(Arrays.toString(res3));
    }

    static class Solution {

        public int[] twoSum(int[] nums, int target) {
//            return twoSum1(nums, target);
            return twoSum2(nums, target);
        }

        public int[] twoSum1(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (map.get(target - nums[i]) != null) {
                    return new int[]{map.get(target - nums[i]), i};
                }
                map.put(nums[i], i);
            }
            return new int[]{};
        }

        public int[] twoSum2(int[] nums, int target) {
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

