package me.yonghong.algo.sort;

import me.yonghong.algo.Solution;

/**
 * 堆排序
 *
 * @author yonghongwang#163.com
 * @since 2021/7/26
 */
public class HeapSort extends Solution {

    public static void main(String[] args) {
        new HeapSort().test();
    }

    @Override
    public void test() {
        int[] nums = {3, 1, 6, 2, 5, 8, 6, 2, 4, 7};
        sort(nums);
        print(nums);
    }

    public void sort(int[] nums) {
        int n = nums.length - 1;
        for (int k = n / 2; k >= 1; k--) {
            sink(nums, k, n);
        }
        while (n > 1) {
            swap(nums, 1, n--);
            sink(nums, 1, n);
        }
    }

    private void sink(int[] nums, int k, int n) {
        while (k * 2 <= n) {
            int j = 2 * k;
            if (j < n && nums[j] < nums[j + 1]) {
                j++;
            }
            if (nums[k] >= nums[j]) {
                break;
            }
            swap(nums, k, j);
            k = j;
        }
    }
}
