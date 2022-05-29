package me.yonghong.algo.lc.lc1401;

import me.yonghong.algo.Solution;

import java.util.Arrays;

/**
 * 1465. 切割后面积最大的蛋糕
 * 1465. Maximum Area of a Piece of Cake After Horizontal and Vertical Cuts
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/maximum-area-of-a-piece-of-cake-after-horizontal-and-vertical-cuts/"></a>
 * @link <a href="https://leetcode.com/problems/maximum-area-of-a-piece-of-cake-after-horizontal-and-vertical-cuts/"></a>
 * @since 2022/02/23
 **/
class Lc1465 extends Solution {

    public static void main(String[] args) {
        new Lc1465().test();
    }

    @Override
    public void test() {
        int res;
        res = maxArea(5, 4, new int[]{1, 2, 4}, new int[]{1, 3});
        System.out.println(res);
        res = maxArea(5, 4, new int[]{3, 1}, new int[]{1});
        System.out.println(res);
        res = maxArea(5, 4, new int[]{3}, new int[]{3});
        System.out.println(res);
    }

    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        long horizonMax = 0, verticalMax = 0;
        int mod = 1000000007;
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);

        for (int i = 1; i < horizontalCuts.length; i++) {
            horizonMax = Math.max(horizonMax, horizontalCuts[i] - horizontalCuts[i - 1]);
        }
        // 补充验证边界切割位置
        horizonMax = Math.max(horizonMax, horizontalCuts[0]);
        horizonMax = Math.max(horizonMax, h - horizontalCuts[horizontalCuts.length - 1]);

        for (int i = 1; i < verticalCuts.length; i++) {
            verticalMax = Math.max(verticalMax, verticalCuts[i] - verticalCuts[i - 1]);
        }
        // 补充验证边界切割位置
        verticalMax = Math.max(verticalMax, verticalCuts[0]);
        verticalMax = Math.max(verticalMax, w - verticalCuts[verticalCuts.length - 1]);

        return (int) ((horizonMax * verticalMax) % mod);
    }
}
