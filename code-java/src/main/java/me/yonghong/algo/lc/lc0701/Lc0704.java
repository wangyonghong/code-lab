package me.yonghong.algo.lc.lc0701;

import me.yonghong.algo.Solution;

/**
 * 704. 二分查找
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/binary-search/"></a>
 * @link <a href="https://leetcode.com/problems/binary-search/"></a>
 * @since 2022/05/29
 **/
public class Lc0704 extends Solution {

    public int search(int[] nums, int target) {
        int l = 0, h = nums.length - 1;
        while (l <= h) { // 为啥是 l <= h，因为数组长度可能为 1
            int mid = l + (h - l) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }

    @Override
    public void test() {
        int res;
        res = search(new int[]{-1, 0, 3, 5, 9, 12}, 9);
        System.out.println(res);
        res = search(new int[]{-1, 0, 3, 5, 9, 12}, 2);
        System.out.println(res);
        res = search(new int[]{5}, 5);
        System.out.println(res);
    }

    public static void main(String[] args) {
        new Lc0704().test();
    }
}
