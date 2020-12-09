package me.yonghong.algo.quiz.sort;

/**
 * 归并排序
 */
public class MergeSort extends Sort {

    private int[] aux;

    @Override
    public void sort(int[] nums) {
        int n = nums.length;
        if (n == 0) return;
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
            aux[k] = nums[k]; // 将数据复制到辅助数组
        }
        for (int k = l; k <= h; k++) {
            if (i > m) {
                nums[k] = aux[j++];
            } else if (j > h) {
                nums[k] = aux[i++];
            } else if (aux[i] <= aux[j]) {
                nums[k] = aux[i++]; // 先进行这一步，保证稳定性
            } else {
                nums[k] = aux[j++];
            }
        }
    }

    public void sortDown2Up(int[] nums) {
        int n = nums.length;
        if (n == 0) return;
        aux = new int[n];
        for (int sz = 1; sz < n; sz += sz) {
            for (int lo = 0; lo < n - sz; lo += sz + sz) {
                merge(nums, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, n - 1));
            }
        }
    }

    public static void main(String[] args) {
        MergeSort sort = new MergeSort();
        int[] nums = {3, 1, 6, 2, 5, 8, 6, 2, 4, 7};
//        sort.sort(nums);
        sort.sortDown2Up(nums);
        sort.print(nums);
    }
}
