package me.yonghong.algo.lc.lc0001

import me.yonghong.algo.Solution

class Lc0080Kt : Solution {

    fun removeDuplicates(nums: IntArray): Int {
        var j = 0
        for (i in nums.indices) {
            if (j < 2 || !(nums[i] == nums[j - 1] && nums[i] == nums[j - 2])) {
                nums[j++] = nums[i]
            }
        }
        return j
    }
}