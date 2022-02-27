package me.yonghong.algo.lc.lc0001

import me.yonghong.algo.Solution
import kotlin.jvm.JvmStatic
import java.util.HashMap

/**
 * 1. 两数之和
 * 1. Two Sum
 *
 * @author yonghongwang#163.com
 * @link [](https://leetcode-cn.com/problems/two-sum/)
 * @link [](https://leetcode.com/problems/two-sum/)
 * @see me.yonghong.algo.lc.lc0101.Lc0167
 *
 * @since 2021/2/21
 */
class Lc0001Kt : Solution() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Lc0001Kt().test()
        }
    }

    override fun test() {
        val res1 = twoSum(intArrayOf(2, 7, 11, 15), 9)
        print(res1)
        val res2 = twoSum(intArrayOf(3, 2, 4), 6)
        print(res2)
        val res3 = twoSum(intArrayOf(3, 3), 6)
        print(res3)
    }

    fun twoSum(nums: IntArray, target: Int): IntArray {
        //            return twoSum1(nums, target);
        return twoSum2(nums, target)
    }

    fun twoSum1(nums: IntArray, target: Int): IntArray {
        val map: MutableMap<Int, Int?> = HashMap()
        for (i in nums.indices) {
            if (map[target - nums[i]] != null) {
                return intArrayOf(map[target - nums[i]]!!, i)
            }
            map[nums[i]] = i
        }
        return intArrayOf()
    }

    fun twoSum2(nums: IntArray, target: Int): IntArray {
        for (i in 0 until nums.size - 1) {
            for (j in i + 1 until nums.size) {
                if (nums[i] + nums[j] == target) {
                    return intArrayOf(i, j)
                }
            }
        }
        return intArrayOf()
    }
}