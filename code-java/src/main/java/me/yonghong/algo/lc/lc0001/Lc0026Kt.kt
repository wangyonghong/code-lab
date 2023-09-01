package me.yonghong.algo.lc.lc0001

import me.yonghong.algo.Solution

class Lc0026Kt : Solution {

    fun removeDuplicates(nums: IntArray): Int {
        var j = 0
        for (i in nums.indices) {
            if (nums[j] != nums[i]) {
                nums[++j] = nums[i]
            }
        }
        return ++j
    }
}