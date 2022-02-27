package me.yonghong.algo.lc.lc0801;

import me.yonghong.algo.Solution;

/**
 * 896. 单调数列
 * 896. Monotonic Array
 * <p>
 * 如果数组是单调递增或单调递减的，那么它是单调的。
 * 如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。
 * 当给定的数组 A 是单调数组时返回 true，否则返回 false。
 * <p>
 * 1 <= A.length <= 50000
 * -100000 <= A[i] <= 100000
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode-cn.com/problems/monotonic-array/"></a>
 * @link <a href="https://leetcode.com/problems/monotonic-array/"></a>
 * @since 2021/2/28
 */
class Lc0896 extends Solution {

    public static void main(String[] args) {
        new Lc0896().test();
    }

    @Override
    public void test() {
        int[] input1 = new int[]{1, 2, 2, 3};
        System.out.println(isMonotonic(input1));
        int[] input2 = new int[]{6, 5, 4, 4};
        System.out.println(isMonotonic(input2));
        int[] input3 = new int[]{1, 3, 2};
        System.out.println(isMonotonic(input3));
        int[] input4 = new int[]{1, 2, 4, 5};
        System.out.println(isMonotonic(input4));
        int[] input5 = new int[]{1, 1, 1};
        System.out.println(isMonotonic(input5));
    }

    public boolean isMonotonic(int[] nums) {
        return isMonotonic1(nums);
    }

    public boolean isMonotonic1(int[] nums) {
        int len = nums.length;
        {
            boolean ok = true;
            for (int i = 1; i < len; i++) {
                if (nums[i - 1] < nums[i]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                return true;
            }
        }
        {
            boolean ok = true;
            for (int i = 1; i < len; i++) {
                if (nums[i - 1] > nums[i]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                return true;
            }
        }
        return false;
    }
}
