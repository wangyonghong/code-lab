package me.yonghong.algo.sort;

import me.yonghong.algo.Solution;

/**
 * 冒泡排序
 *
 * @author yonghongwang#163.com
 * @since 2021/7/26
 */
public class BubbleSort implements Solution {

    public static void main(String[] args) {
        new BubbleSort().test();
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
        for (int i = 0; i < n; i++) {
            // 提前退出标志位
            boolean flag = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    /**
     * 冒泡排序的改进：在每一轮排序后记录最后一次元素交换的位置，作为下次比较的边界
     * 对于边界外的元素在下次循环中无需比较
     */
    public void sortOptimize1(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return;
        }
        int lastExchange = 0;
        // 无序数据的边界，每次只需要比较到这里即可退出
        int sortBorder = n - 1;
        for (int i = 0; i < n; i++) {
            boolean flag = false;
            for (int j = 0; j < sortBorder; j++) {
                if (nums[j] > nums[j + 1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = tmp;
                    flag = true;
                    // 更新最后一次交换的位置
                    lastExchange = j;
                }
            }
            sortBorder = lastExchange;
            if (!flag) {
                // 没有数据交换，提前退出
                break;
            }
        }
    }
}
