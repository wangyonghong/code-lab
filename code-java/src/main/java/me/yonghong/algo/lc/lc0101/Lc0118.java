package me.yonghong.algo.lc.lc0101;

import me.yonghong.algo.Solution;

import java.util.ArrayList;
import java.util.List;

/**
 * 118. 杨辉三角
 * 118. Pascal's Triangle
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode-cn.com/problems/pascals-triangle/"></a>
 * @link <a href="https://leetcode.com/problems/pascals-triangle/"></a>
 * @since 2021/8/19
 **/
public class Lc0118 implements Solution {

    public static void main(String[] args) {
        new Lc0118().test();
    }

    @Override
    public void test() {
        List<List<Integer>> res = generate(5);
        printListList(res);
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    list.add(1);
                } else {
                    List<Integer> pre = res.get(i - 1);
                    list.add(pre.get(j - 1) + pre.get(j));
                }
            }
            res.add(list);
        }
        return res;
    }
}
