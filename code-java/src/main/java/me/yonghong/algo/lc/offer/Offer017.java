package me.yonghong.algo.lc.offer;

import me.yonghong.algo.Solution;

import java.util.stream.IntStream;

/**
 * 剑指 Offer 17. 打印从1到最大的n位数
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/"></a>
 * @since 2021/8/2
 **/
public class Offer017 implements Solution {

    public static void main(String[] args) {
        new Offer017().test();
    }

    @Override
    public void test() {
        int[] res = printNumbers(3);
        print(res);
    }

    public int[] printNumbers(int n) {
        return IntStream.range(1, (int) (Math.pow(10, n))).toArray();
    }
}
