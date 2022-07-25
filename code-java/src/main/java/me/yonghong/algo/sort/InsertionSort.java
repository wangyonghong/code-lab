package me.yonghong.algo.sort;

import me.yonghong.algo.Solution;

/**
 * 插入排序
 *
 * @author yonghongwang#163.com
 * @since 2021/7/26
 */
public class InsertionSort implements Solution {

    public static void main(String[] args) {
        new InsertionSort().test();
    }

    @Override
    public void test() {
        int[] nums = {3, 1, 6, 2, 5, 8, 6, 2, 4, 7};
        sort(nums);
        print(nums);
    }

    public static void sort(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return;
        }
        for (int i = 1; i < n; i++) {
            int value = nums[i];
            int j = i - 1;
            // 从后向前搜索
            for (; j >= 0; j--) {
                if (nums[j] > value) {
                    nums[j + 1] = nums[j];
                } else {
                    break;
                }
            }
            nums[j + 1] = value;
        }
    }
}
