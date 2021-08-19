package me.yonghong.algo.lc.lc0201;

import java.util.ArrayList;
import java.util.List;

import me.yonghong.algo.Solution;

/**
 * 293. 翻转游戏
 * 293. Flip Game
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode-cn.com/problems/flip-game/"></a>
 * @link <a href="https://leetcode.com/problems/flip-game/"></a>
 * @since 2021/8/19
 */
public class Lc0293 implements Solution {

    public static void main(String[] args) {

    }

    @Override
    public void test() {

    }

    public List<String> generatePossibleNextMoves(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() < 2) {
            return res;
        }
        char[] chs = s.toCharArray();
        for (int i = 0; i < chs.length - 1; i++) {
            if (chs[i] == '+' && chs[i + 1] == '+') {
                chs[i] = '-';
                chs[i + 1] = '-';
                res.add(new String(chs));
                chs[i] = '+';
                chs[i + 1] = '+';
            }
        }
        return res;
    }
}
