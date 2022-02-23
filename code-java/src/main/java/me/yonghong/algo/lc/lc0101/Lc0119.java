package me.yonghong.algo.lc.lc0101;

import me.yonghong.algo.Solution;

import java.util.ArrayList;
import java.util.List;

/**
 * 119. 杨辉三角 II
 * 119. Pascal's Triangle II
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode-cn.com/problems/pascals-triangle-ii/"></a>
 * @link <a href="https://leetcode.com/problems/pascals-triangle-ii/"></a>
 * @since 2021/8/19
 **/
class Lc0119 implements Solution {

    public static void main(String[] args) {
        new Lc0119().test();
    }

    @Override
    public void test() {
        List<Integer> res = getRow(5);
        printList(res);
    }

    public List<Integer> getRow(int rowIndex) {
        List<Integer> pre = new ArrayList<>(), cur = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            cur = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    cur.add(1);
                } else {
                    cur.add(pre.get(j - 1) + pre.get(j));
                }
            }
            pre = cur;
        }
        return cur;
    }
}
