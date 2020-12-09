package me.yonghong.algo.hug.sort;

public class QuickSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    public void sort(T[] nums) {
        sort(nums, 0, nums.length - 1);
    }

    private void sort(T[] nums, int l, int h) {
        if (l >= h) {
            return;
        }
        int j = partition(nums, l, h);
        sort(nums, l, j - 1);
        sort(nums, j + 1, h);
    }

    private int partition(T[] nums, int l, int h) {
        T v = nums[l];
        int i = l, j = h;
        while (i < j) {
            while (i < j && less(v, nums[j])) {
                j--;
            }
            if (i < j) {
                nums[i++] = nums[j];
            }
            while (i < j && less(nums[i], v)) {
                i++;
            }
            if (i < j) {
                nums[j--] = nums[i];
            }
        }
        nums[i] = v;
        return i;
    }

    public static void main(String[] args) {
        QuickSort<Integer> quickSort = new QuickSort<>();
        Integer[] nums = {3, 1, 6, 2, 5, 8, 6, 2, 4, 7};
        quickSort.sort(nums);
        quickSort.print(nums);
    }
}
