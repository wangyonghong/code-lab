package me.yonghong.algo.lc.lc0201;

import me.yonghong.algo.Solution;

/**
 * 215. 数组中的第K个最大元素
 * 215. Kth Largest Element in an Array
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode-cn.com/problems/kth-largest-element-in-an-array/"></a>
 * @link <a href="https://leetcode.com/problems/kth-largest-element-in-an-array/"></a>
 * @since 2021/8/12
 **/
public class Lc0215 implements Solution {

    public static void main(String[] args) {

    }

    @Override
    public void test() {

    }

    public int findKthLargest(int[] nums, int k) {
        sort(nums, 0, nums.length - 1);
        return nums[nums.length - k];
    }

    private void sort(int[] nums, int l, int h) {
        if (l >= h) {
            return;
        }
        int j = partition(nums, l, h);
        sort(nums, l, j - 1);
        sort(nums, j + 1, h);
    }

    private int partition(int[] nums, int l, int h) {
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
