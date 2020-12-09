package me.yonghong.algo.quiz.sort;

public abstract class Sort {

    protected void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public abstract void sort(int[] nums);

    protected void print(int[] nums) {
        for (int t : nums) {
            System.out.print(t + " ");
        }
        System.out.println();
    }
}
