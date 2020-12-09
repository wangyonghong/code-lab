package me.yonghong.algo.quiz.sort;

public class QuickSort extends Sort {

    public void sort(int[] nums) {
        sort(nums, 0, nums.length - 1);
//        sort_new(nums, 0, nums.length - 1);
    }

    private void sort(int[] nums, int l, int h) {
        if (l >= h) return;
        int v = nums[l];
        int i = l, j = h;
        while (i < j) {
            while (i < j && nums[j] >= v) j--;
            if (i < j) nums[i++] = nums[j];
            while (i < j && nums[i] <= v) i++;
            if (i < j) nums[j--] = nums[i];
        }
        // 此时 i = j
        nums[i] = v;
        sort(nums, l, i - 1);
        sort(nums, i + 1, h);
    }

    /**
     * 改进的快排
     * a. 单边递归优化
     * b. 基准值选取优化
     * c. partition操作优化
     */
    private void sort_new(int[] nums, int l, int h) {

    }

    private int select(int[] nums, int a, int b, int c) {
        if (nums[a] > nums[b]) swap(nums, a, b);
        if (nums[a] > nums[c]) swap(nums, a, c);
        if (nums[b] > nums[c]) swap(nums, b, c);
        return nums[b];
    }

    public static void main(String[] args) {
        QuickSort sort = new QuickSort();
        int[] nums = {3, 1, 9, 10, 6, 2, 5, 8, 6, 2, 4, 7};
        sort.sort(nums);
        sort.print(nums);
    }
}
