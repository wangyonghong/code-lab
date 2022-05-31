package me.yonghong.algo.lc.lc0901;

import me.yonghong.algo.Solution;

/**
 * 977. 有序数组的平方
 * 977. Squares of a Sorted Array
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/squares-of-a-sorted-array/"></a>
 * @link <a href="https://leetcode.com/problems/squares-of-a-sorted-array/"></a>
 * @since 2022/5/30
 */
public class Lc0977 implements Solution {

    public int[] sortedSquares(int[] nums) {
        int l = 0, r = nums.length - 1, c = nums.length - 1;
        int[] res = new int[nums.length];
        while (l <= r) {
            if (Math.abs(nums[l]) >= Math.abs(nums[r])) {
                res[c--] = nums[l] * nums[l];
                l++;
            } else {
                res[c--] = nums[r] * nums[r];
                r--;
            }
        }
        return res;
    }

    @Override
    public void test() {
        int[] res;
        res = sortedSquares(new int[]{-4, -1, 0, 3, 10});
        print(res);

        res = sortedSquares(new int[]{-7, -3, 2, 3, 11});
        print(res);
    }

    public static void main(String[] args) {
        new Lc0977().test();
    }
}
