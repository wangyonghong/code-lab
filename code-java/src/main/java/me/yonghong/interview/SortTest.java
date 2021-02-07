package me.yonghong.interview;

/**
 * @author yonghongwang#163.com
 * @since 2020/12/15
 **/
public class SortTest {

    public static void main(String[] args) {
        int[] nums = {3, 4, 2, 5, 9, 6, 12, 1};
        sort(nums, 0, nums.length - 1);
        for (int num : nums) {
            System.out.println(num);
        }
    }

    public static void sort(int[] nums, int l, int h) {
        if (l >= h) return;
        int i = l, j = h;
        int mid = nums[l];
        while (i <= j) {
            while (i <= j && mid <= nums[j]) j--;
            if (i <= j) nums[i++] = nums[j];
            while (i <= j && mid >= nums[i]) i++;
            if (i <= j) nums[j--] = nums[i];
        }
        nums[i] = mid;
        sort(nums, l, i - 1);
        sort(nums, i + 1, h);
    }
}
