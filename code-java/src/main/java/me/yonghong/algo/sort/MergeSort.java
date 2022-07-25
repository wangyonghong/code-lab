package me.yonghong.algo.sort;

import me.yonghong.algo.Solution;

/**
 * 归并排序
 *
 * @author yonghongwang#163.com
 * @since 2021/7/26
 */
public class MergeSort implements Solution {

    private int[] aux;

    public static void main(String[] args) {
        new MergeSort().test();
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
        aux = new int[n];
        sort(nums, 0, n - 1);
    }

    private void sort(int[] nums, int l, int h) {
        if (h <= l) {
            return;
        }
        int mid = l + (h - l) / 2;
        sort(nums, l, mid);
        sort(nums, mid + 1, h);
        merge(nums, l, mid, h);
    }

    private void merge(int[] nums, int l, int m, int h) {
        int i = l, j = m + 1;
        for (int k = l; k <= h; k++) {
            // 将数据复制到辅助数组
            aux[k] = nums[k];
        }
        for (int k = l; k <= h; k++) {
            if (i > m) {
                nums[k] = aux[j++];
            } else if (j > h) {
                nums[k] = aux[i++];
            } else if (aux[i] <= aux[j]) {
                // 先进行这一步，保证稳定性
                nums[k] = aux[i++];
            } else {
                nums[k] = aux[j++];
            }
        }
    }

    public void sortDown2Up(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return;
        }
        aux = new int[n];
        for (int sz = 1; sz < n; sz += sz) {
            for (int lo = 0; lo < n - sz; lo += sz + sz) {
                merge(nums, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, n - 1));
            }
        }
    }
}
