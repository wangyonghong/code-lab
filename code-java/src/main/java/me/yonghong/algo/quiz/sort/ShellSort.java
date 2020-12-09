package me.yonghong.algo.quiz.sort;

/**
 * 希尔排序
 */
public class ShellSort extends Sort {

    @Override
    public void sort(int[] nums) {
        int n = nums.length;
        if (n == 0) return;
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

    public static void main(String[] args) {
        ShellSort sort = new ShellSort();
        int[] nums = {3, 1, 6, 2, 5, 8, 6, 2, 4, 7};
        sort.sort(nums);
        sort.print(nums);
    }
}
