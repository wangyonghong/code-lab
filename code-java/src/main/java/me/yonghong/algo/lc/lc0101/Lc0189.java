package me.yonghong.algo.lc.lc0101;

import me.yonghong.algo.Solution;
import me.yonghong.algo.SolutionUtils;

/**
 * 189. 轮转数组
 * 189. Rotate Array
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/rotate-array/"></a>
 * @link <a href="https://leetcode.com/problems/rotate-array/"></a>
 * @since 2022/5/30
 **/
public interface Lc0189 extends Solution {

    void rotate(int[] nums, int k);

    @Override
    default void test() {
        rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 3);
        rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 10);
        rotate(new int[]{-1, -100, 3, 99}, 2);
    }

    class Solution01 implements Lc0189 {

        @Override
        public void rotate(int[] nums, int k) {
            int n = nums.length;
            k = k % n;
            int count = gcd(k, n);
            for (int start = 0; start < count; ++start) {
                int current = start;
                int prev = nums[start];
                do {
                    int next = (current + k) % n;
                    int temp = nums[next];
                    nums[next] = prev;
                    prev = temp;
                    current = next;
                } while (start != current);
            }

            // debug
            print(nums);
        }

        public int gcd(int x, int y) {
            return y > 0 ? gcd(y, x % y) : x;
        }
    }

    class Solution02 implements Lc0189 {

        @Override
        public void rotate(int[] nums, int k) {
            int n = nums.length;
            int[] newArr = new int[n];
            for (int i = 0; i < n; ++i) {
                newArr[(i + k) % n] = nums[i];
            }
            System.arraycopy(newArr, 0, nums, 0, n);

            // debug
            print(nums);
        }
    }

    static void main(String[] args) {
        SolutionUtils.runTest(Lc0189.class);
    }
}
