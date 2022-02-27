package me.yonghong.algo.lc.lc0801;

import me.yonghong.algo.Solution;

/**
 * 867. 转置矩阵
 * 867. Transpose Matrix
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode-cn.com/problems/transpose-matrix/"></a>
 * @link <a href="https://leetcode.com/problems/transpose-matrix/"></a>
 * @since 2021/8/19
 */
class Lc0867 extends Solution {

    public int[][] transpose(int[][] matrix) {
        int wid = matrix.length;
        int len = matrix[0].length;
        int[][] res = new int[len][wid];
        for (int j = 0; j < len; j++) {
            for (int i = 0; i < wid; i++) {
                res[j][i] = matrix[i][j];
            }
        }
        return res;
    }
}
