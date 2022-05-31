package me.yonghong.algo.lc.lc0801;

import me.yonghong.algo.Solution;

/**
 * 838. 推多米诺
 * 838. Push Dominoes
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/push-dominoes/"></a>
 * @link <a href="https://leetcode.com/problems/push-dominoes/"></a>
 * @since 2022/02/21
 **/
class Lc0838 implements Solution {

    public static void main(String[] args) {
        new Lc0838().test();
    }

    @Override
    public void test() {
    }

    public String pushDominoes(String dominoes) {
        while (!dominoes.equals(dominoes = dominoes
                .replace("R.L", "T")
                .replace(".L", "LL")
                .replace("R.", "RR")
                .replace("T", "R.L"))) ;
        return dominoes;
    }
}
