package me.yonghong.algo.lc.lc0201;

import me.yonghong.algo.Solution;

/**
 * 294. 翻转游戏 II
 * 294. Flip Game II
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/flip-game-ii/"></a>
 * @link <a href="https://leetcode.com/problems/flip-game-ii/"></a>
 * @since 2021/8/19
 */
class Lc0294 extends Solution {

    public static void main(String[] args) {
        new Lc0294().test();
    }

    @Override
    public void test() {
        System.out.println(canWin("++++"));
        System.out.println(canWin("+"));
    }

    public boolean canWin(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }
        char[] chs = s.toCharArray();
        for (int i = 0; i < chs.length - 1; i++) {
            if (chs[i] == '+' && chs[i + 1] == '+') {
                chs[i] = '-';
                chs[i + 1] = '-';
                if (!canWin(new String(chs))) {
                    return true;
                }
                chs[i] = '+';
                chs[i + 1] = '+';
            }
        }
        return false;
    }
}
