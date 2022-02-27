package me.yonghong.algo.sort;

import me.yonghong.algo.Solution;

/**
 * 选择排序
 *
 * @author yonghongwang#163.com
 * @since 2021/7/26
 */
public class SelectionSort extends Solution {

    public static void main(String[] args) {
        new SelectionSort().test();
    }

    @Override
    public void test() {
        int[] nums = {3, 1, 6, 2, 5, 8, 6, 2, 4, 7};
        sort(nums);
        print(nums);
    }

    public void sort(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return;
        }
        for (int i = 0; i < n - 1; i++) {
            int min = nums[i];
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (nums[j] < min) {
                    min = nums[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int tmp = nums[i];
                nums[i] = nums[minIndex];
                nums[minIndex] = tmp;
            }
        }
    }
}
