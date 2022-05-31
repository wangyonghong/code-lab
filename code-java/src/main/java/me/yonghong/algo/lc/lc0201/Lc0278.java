package me.yonghong.algo.lc.lc0201;

import me.yonghong.algo.Solution;

/**
 * 278. 第一个错误的版本
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/first-bad-version/"></a>
 * @link <a href="https://leetcode.com/problems/first-bad-version/"></a>
 * @since 2022/05/30
 **/
public class Lc0278 implements Solution {

    private boolean isBadVersion(int n) {
        return false;
    }

    public int firstBadVersion(int n) {
        int l = 0, h = n - 1;
        while (l <= h) { // 为啥是 l <= h，因为数组长度可能为 1
            int mid = l + (h - l) / 2;
            if (isBadVersion(mid)) {
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
