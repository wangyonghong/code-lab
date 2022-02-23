package me.yonghong.algo.lc.lc1701;

import me.yonghong.algo.Solution;

import java.util.HashSet;
import java.util.Set;

/**
 * 1748. 唯一元素的和
 * 1748. Sum of Unique Elements
 * <p>
 * 给你一个整数数组nums。数组中唯一元素是那些只出现恰好一次的元素。
 * 请你返回 nums中唯一元素的和。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3,2]
 * 输出：4
 * 解释：唯一元素为 [1,3] ，和为 4 。
 * <p>
 * 示例 2：
 * 输入：nums = [1,1,1,1,1]
 * 输出：0
 * 解释：没有唯一元素，和为 0 。
 * <p>
 * 示例 3 ：
 * 输入：nums = [1,2,3,4,5]
 * 输出：15
 * 解释：唯一元素为 [1,2,3,4,5] ，和为 15 。
 * <p>
 * 提示：
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode-cn.com/problems/sum-of-unique-elements/"></a>
 * @link <a href="https://leetcode.com/problems/sum-of-unique-elements/"></a>
 * @since 2022/02/22
 **/
class Lc1748 implements Solution {

    public static void main(String[] args) {
        new Lc1748().test();
    }

    @Override
    public void test() {
        int res;
        res = sumOfUnique(new int[]{1, 2, 3, 2});
        System.out.println(res);
        res = sumOfUnique(new int[]{1, 1, 1, 1, 1});
        System.out.println(res);
        res = sumOfUnique(new int[]{1, 2, 3, 4, 5});
        System.out.println(res);
    }

    public int sumOfUnique(int[] nums) {
        Set<Integer> all = new HashSet<>();
        Set<Integer> one = new HashSet<>();
        for (int num : nums) {
            if (all.contains(num)) {
                one.remove(num);
            } else {
                all.add(num);
                one.add(num);
            }
        }
        int sum = 0;
        for (int num : one) {
            sum += num;
        }
        return sum;
    }
}
