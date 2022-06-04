package me.yonghong.algo.lc.lc0501;

import me.yonghong.algo.Solution;
import me.yonghong.algo.SolutionUtils;

/**
 * 557. 反转字符串中的单词 III
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/reverse-words-in-a-string-iii/"></a>
 * @link <a href="https://leetcode.com/problems/reverse-words-in-a-string-iii/"></a>
 * @since 2022/6/1
 */
public interface Lc0557 extends Solution {

    String reverseWords(String s);

    @Override
    default void test() {
        String res;
        res = reverseWords("Let's take LeetCode contest");
        System.out.println(res);
        res = reverseWords("God Ding");
        System.out.println(res);
    }

    class Solution01 implements Lc0557 {
        @Override
        public String reverseWords(String s) {
            char[] chs = s.toCharArray();
            int i = -1, j = -1;
            while (j < chs.length) {
                j = j + 1;
                i = j;
                while (j < chs.length && chs[j] != ' ') {
                    j++;
                }
                int l = i, r = j - 1;
                while (l < r) {
                    char t = chs[l];
                    chs[l++] = chs[r];
                    chs[r--] = t;
                }
            }
            return new String(chs);
        }
    }

    public static void main(String[] args) {
        SolutionUtils.runTest(Lc0557.class);
    }
}
