package me.yonghong.algo.lc.lc0101;

import me.yonghong.algo.Solution;

/**
 * 167. 两数之和 II - 输入有序数组
 * 167. Two Sum II - Input array is sorted
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/"></a>
 * @link <a href="https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/"></a>
 * @see me.yonghong.algo.lc.lc0001.Lc0001
 * @since 2021/8/19
 **/
class Lc0167 extends Solution {

    public static void main(String[] args) {
        new Lc0167().test();
    }

    @Override
    public void test() {
        int[] res = twoSum(new int[]{2, 7, 11, 15}, 9);
        print(res);
    }

    public int[] twoSum(int[] numbers, int target) {
        int low = 0, high = numbers.length - 1;
        while (low < high) {
            int sum = numbers[low] + numbers[high];
            if (sum == target) {
                return new int[]{low + 1, high + 1};
            } else if (sum < target) {
                ++low;
            } else {
                --high;
            }
        }
        return new int[]{-1, -1};
    }
}
