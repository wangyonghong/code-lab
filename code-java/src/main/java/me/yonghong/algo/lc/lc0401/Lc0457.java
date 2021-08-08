package me.yonghong.algo.lc.lc0401;

import me.yonghong.algo.Solution;

/**
 * 457. 环形数组是否存在循环
 * 457. Circular Array Loop
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode-cn.com/problems/circular-array-loop/"></a>
 * @link <a href="https://leetcode.com/problems/circular-array-loop/"></a>
 * @see me.yonghong.algo.lc.lc0101.Lc0141
 * @since 2021/8/7
 **/
public class Lc0457 implements Solution {

    public static void main(String[] args) {
        new Lc0457().test();
    }

    @Override
    public void test() {
        System.out.println(circularArrayLoop(new int[]{2, -1, 1, 2, 2}));
        System.out.println(circularArrayLoop(new int[]{-1, 2}));
        System.out.println(circularArrayLoop(new int[]{-2, 1, -1, -2, -2}));
    }

    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int fast = i, slow = i;
            while (nums[slow] * nums[fast] > 0 && nums[slow] * nums[next(nums, fast)] > 0) {
                slow = next(nums, slow);
                fast = next(nums, next(nums, fast));
                if (slow == fast) {
                    // 循环的长度需要大于1
                    if (slow != next(nums, slow)) {
                        return true;
                    } else {
                        break;
                    }
                }
            }
        }
        return false;
    }

    private int next(int[] nums, int i) {
        // 注意取余后可能是负数
        return ((i + nums[i]) % nums.length + nums.length) % nums.length;
    }
}
