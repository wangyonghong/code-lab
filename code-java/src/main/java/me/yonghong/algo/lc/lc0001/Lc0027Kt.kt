package me.yonghong.algo.lc.lc0001

import me.yonghong.algo.Solution

class Lc0027Kt : Solution {

    fun removeElement(nums: IntArray, `val`: Int): Int {
        var j = 0
        for (i in nums.indices) {
            if (nums[i] != `val`) {
                nums[j++] = nums[i]
            }
        }
        return j
    }
}