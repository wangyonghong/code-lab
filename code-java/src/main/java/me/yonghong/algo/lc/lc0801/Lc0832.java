package me.yonghong.algo.lc.lc0801;

import me.yonghong.algo.Solution;

/**
 * 832. 翻转图像
 * 832. Flipping an Image
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode-cn.com/problems/flipping-an-image/"></a>
 * @link <a href="https://leetcode.com/problems/flipping-an-image/"></a>
 * @since 2021/8/19
 */
class Lc0832 extends Solution {

    public static void main(String[] args) {

    }

    @Override
    public void test() {
    }

    public int[][] flipAndInvertImage(int[][] image) {
        int len = image.length;
        int i, j, k = 0;
        while (k < len) {
            i = 0;
            j = len - 1;
            while (i < j) {
                int tmp = image[k][i];
                image[k][i++] = 1 - image[k][j];
                image[k][j--] = 1 - tmp;
            }
            if (i == j) {
                image[k][i] = 1 - image[k][i];
            }
            k++;
        }
        return image;
    }
}
