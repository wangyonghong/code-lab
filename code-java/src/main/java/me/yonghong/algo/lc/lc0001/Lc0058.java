package me.yonghong.algo.lc.lc0001;

import me.yonghong.algo.Solution;

/**
 * 58. 最后一个单词的长度
 * 58. Length of Last Word
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/length-of-last-word/"></a>
 * @link <a href="https://leetcode.com/problems/length-of-last-word/"></a>
 * @since 2021/8/8
 **/
class Lc0058 extends Solution {

    public static void main(String[] args) {
        new Lc0058().test();
    }

    @Override
    public void test() {
        System.out.println(lengthOfLastWord("Hello World"));
        System.out.println(lengthOfLastWord("   fly me   to   the moon  "));
        System.out.println(lengthOfLastWord("luffy is still joyboy"));
        System.out.println(lengthOfLastWord("   "));
        System.out.println(lengthOfLastWord(null));
    }

    public int lengthOfLastWord(String s) {
        if (s == null) {
            return 0;
        }
        int len = s.length();
        int i = len - 1;
        while (i >= 0 && s.charAt(i) == ' ') {
            i--;
        }
        int res = 0;
        while (i >= 0 && s.charAt(i) != ' ') {
            i--;
            res++;
        }
        return res;
    }
}
