package me.yonghong.algo.sort;

import me.yonghong.algo.Solution;

/**
 * 希尔排序
 *
 * @author yonghongwang#163.com
 * @since 2021/7/26
 */
public class ShellSort implements Solution {

    public static void main(String[] args) {
        new ShellSort().test();
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
        int[] ds = {5, 3, 1};
        for (int i = 0; i < 3; i++) {
            shellSort(nums, n, ds[i]);
        }
    }

    private void shellSort(int[] nums, int n, int d) {
        for (int i = d; i < n; i++) {
            int value = nums[i];
            int j = i - d;
            for (; j >= 0; j -= d) {
                if (value < nums[j]) {
                    nums[j + d] = nums[j];
                } else {
                    break;
                }
            }
            nums[j + d] = value;
        }
    }

}
