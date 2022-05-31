package me.yonghong.algo.lc.lc0101;

import me.yonghong.algo.Solution;

/**
 * 153. 寻找旋转排序数组中的最小值
 * 153. Find Minimum in Rotated Sorted Array
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array/"></a>
 * @link <a href="https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/"></a>
 * @since 2021/8/8
 **/
class Lc0153 implements Solution {

    public static void main(String[] args) {
        new Lc0153().test();
    }

    @Override
    public void test() {
        int min = findMin(new int[]{3, 4, 5, 1, 2});
        System.out.println(min);
    }

    public int findMin(int[] nums) {
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int mid = l + (h - l) / 2;
            if (nums[mid] < nums[h]) {
                h = mid;
            } else {
                l = mid + 1;
            }
        }
        return nums[l];
    }
}
