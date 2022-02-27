package me.yonghong.algo.sort;

import me.yonghong.algo.Solution;

/**
 * 快速排序
 *
 * @author yonghongwang#163.com
 * @since 2021/7/26
 */
public class QuickSort extends Solution {

    public static void main(String[] args) {
        new QuickSort().test();
    }

    @Override
    public void test() {
        int[] nums = {3, 1, 6, 2, 5, 8, 6, 2, 4, 7};
        sort(nums);
        print(nums);
    }

    public void sort(int[] nums) {
        sort(nums, 0, nums.length - 1);
    }

    public void sort(int[] nums, int l, int h) {
        if (l >= h) {
            return;
        }
        int k = partition(nums, l, h);
        sort(nums, l, k - 1);
        sort(nums, k + 1, h);
    }

    public int partition(int[] nums, int l, int h) {
        int v = nums[l];
        int i = l, j = h;
        while (i < j) {
            while (i < j && v < nums[j]) {
                j--;
            }
            if (i < j) {
                nums[i++] = nums[j];
            }
            while (i < j && nums[i] < v) {
                i++;
            }
            if (i < j) {
                nums[j--] = nums[i];
            }
        }
        nums[i] = v;
        return i;
    }
}
