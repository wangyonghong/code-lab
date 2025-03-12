package me.yonghong.algo.lc.lc0101;

import me.yonghong.algo.Solution;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Lc0128 implements Solution {
    /**
     * O(n)
     */
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int max = 1;
        for (int num : set) {
            // 如果集合里有 num-1，则不能作为遍历的开始
            if (!set.contains(num - 1)) {
                int count = 1;
                while (set.contains(++num)) {
                    count++;
                }
                max = Math.max(max, count);
            }
        }
        return max;
    }

    /**
     * O(n^2)
     */
    public int longestConsecutive2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, num);
        }
        int max = 0;
        for (int num : nums) {
            int r = num;
            while (map.containsKey(r + 1)) {
                r = map.get(r + 1);
            }
            map.put(num, r);
            max = Math.max(max, r - num + 1);
        }
        return max;
    }
}
