package me.yonghong.algo.lc.lc0901;

import me.yonghong.algo.Solution;

/**
 * 912. 排序数组
 * 912. Sort an Array
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/sort-an-array/"></a>
 * @link <a href="https://leetcode.com/problems/sort-an-array/"></a>
 * @since 2021/8/8
 **/
class Lc0912 extends Solution {

    public static void main(String[] args) {
        new Lc0912().test();
    }

    @Override
    public void test() {
        int[] nums = {3, 1, 6, 2, 5, 8, 6, 2, 4, 7};
        sortArray(nums);
        print(nums);
    }

    public int[] sortArray(int[] nums) {
        sort(nums, 0, nums.length - 1);
        return nums;
    }

    private void sort(int[] nums, int l, int h) {
        if (l > h) {
            return;
        }
        int j = partition(nums, l, h);
        sort(nums, l, j - 1);
        sort(nums, j + 1, h);
    }

    private int partition(int[] nums, int l, int h) {
        int k = nums[l];
        while (l < h) {
            while (l < h && nums[h] > k) {
                h--;
            }
            if (l < h) {
                nums[l++] = nums[h];
            }
            while (l < h && nums[l] < k) {
                l++;
            }
            if (l < h) {
                nums[h--] = nums[l];
            }
        }
        nums[l] = k;
        return l;
    }
}
