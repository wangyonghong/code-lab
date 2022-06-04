package me.yonghong.algo.lc.lc0201;

import me.yonghong.algo.Solution;
import me.yonghong.algo.SolutionUtils;

/**
 * 283. 移动零
 * 283. Move Zeroes
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/move-zeroes/"></a>
 * @link <a href="https://leetcode.com/problems/move-zeroes/"></a>
 * @since 2022/05/30
 **/
public interface Lc0283 extends Solution {

    public void moveZeroes(int[] nums);

    @Override
    default void test() {
        moveZeroes(new int[]{0, 1, 0, 3, 12});
        moveZeroes(new int[]{0});
    }

    class Solution01 implements Lc0283 {

        @Override
        public void moveZeroes(int[] nums) {
            int i = 0, j = 0;
            while (j < nums.length) {
                if (nums[j] != 0) {
                    nums[i++] = nums[j];
                }
                j++;
            }
            while (i < nums.length) {
                nums[i++] = 0;
            }

            // debug
            print(nums);
        }
    }

    public static void main(String[] args) {
        SolutionUtils.runTest(Lc0283.class);
    }
}
