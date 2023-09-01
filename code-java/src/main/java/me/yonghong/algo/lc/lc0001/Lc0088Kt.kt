package me.yonghong.algo.lc.lc0001

import me.yonghong.algo.Solution

class Lc0088Kt : Solution {

    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
        var i = m - 1
        var j = n - 1
        for (k in m + n - 1 downTo 0) {
            if (i >= 0 && j >= 0 && nums1[i] > nums2[j]) {
                nums1[k] = nums1[i]
                nums1[i--] = 0
            } else if (j >= 0) {
                nums1[k] = nums2[j--]
            }
        }
    }
}