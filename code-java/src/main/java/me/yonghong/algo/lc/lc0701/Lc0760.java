package me.yonghong.algo.lc.lc0701;

import me.yonghong.algo.Solution;

import java.util.HashMap;
import java.util.Map;

/**
 * 760. 找出变位映射
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode-cn.com/problems/find-anagram-mappings/"></a>
 * @link <a href="https://leetcode.com/problems/find-anagram-mappings/"></a>
 * @since 2021/8/19
 **/
public class Lc0760 implements Solution {

    public static void main(String[] args) {
        new Lc0760().test();
    }

    @Override
    public void test() {
        int[] res = anagramMappings(new int[]{12, 28, 46, 32, 50}, new int[]{50, 12, 32, 46, 28});
        print(res);
    }

    public int[] anagramMappings(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            map.put(nums2[i], i);
        }
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = map.get(nums1[i]);
        }
        return nums1;
    }
}
