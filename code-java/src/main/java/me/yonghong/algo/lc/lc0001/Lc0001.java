package me.yonghong.algo.lc.lc0001;

import me.yonghong.algo.Solution;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和
 * 1. Two Sum
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode-cn.com/problems/two-sum/"></a>
 * @link <a href="https://leetcode.com/problems/two-sum/"></a>
 * @see me.yonghong.algo.lc.lc0101.Lc0167
 * @since 2021/2/21
 **/
class Lc0001 implements Solution {

    public static void main(String[] args) {
        new Lc0001().test();
    }

    @Override
    public void test() {
        int[] res1 = twoSum(new int[]{2, 7, 11, 15}, 9);
        print(res1);
        int[] res2 = twoSum(new int[]{3, 2, 4}, 6);
        print(res2);
        int[] res3 = twoSum(new int[]{3, 3}, 6);
        print(res3);
    }

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

