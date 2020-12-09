package me.yonghong.algo.quiz.sort;

/**
 * 插入排序
 */
public class InsertionSort extends Sort {

    @Override
    public void sort(int[] nums) {
        int n = nums.length;
        if (n == 0) return;
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

    public static void main(String[] args) {
        InsertionSort sort = new InsertionSort();
        int[] nums = {3, 1, 6, 2, 5, 8, 6, 2, 4, 7};
        sort.sort(nums);
        sort.print(nums);
    }
}
