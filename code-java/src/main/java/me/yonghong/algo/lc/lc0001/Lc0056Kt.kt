package me.yonghong.algo.lc.lc0001

import me.yonghong.algo.Solution
import kotlin.math.max

class Lc0056Kt : Solution {

    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        intervals.sortWith { o1, o2 -> o1[0] - o2[0] }
        val merged = mutableListOf<IntArray>()
        for (interval in intervals) {
            if (merged.size == 0 || merged[merged.size - 1][1] < interval[0]) {
                merged.add(intArrayOf(interval[0], interval[1]))
            } else {
                merged[merged.size - 1][1] = max(merged[merged.size - 1][1], interval[1])
            }
        }
        return merged.toTypedArray()
    }
}