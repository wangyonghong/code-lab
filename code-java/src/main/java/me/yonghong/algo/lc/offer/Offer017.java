package me.yonghong.algo.lc.offer;

import me.yonghong.algo.Solution;

import java.util.stream.IntStream;

/**
 * @author yonghongwang#163.com
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
