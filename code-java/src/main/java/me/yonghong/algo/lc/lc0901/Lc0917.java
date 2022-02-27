package me.yonghong.algo.lc.lc0901;

import me.yonghong.algo.Solution;

/**
 * 917. 仅仅反转字母
 * 917. Reverse Only Letters
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode-cn.com/problems/reverse-only-letters/"></a>
 * @link <a href="https://leetcode.com/problems/reverse-only-letters/"></a>
 * @since 2021/8/18
 */
class Lc0917 extends Solution {

    public static void main(String[] args) {
        new Lc0917().test();
    }

    @Override
    public void test() {
        System.out.println(reverseOnlyLetters("7_28]"));
        System.out.println(reverseOnlyLetters("7_28]abc33"));
    }

    public String reverseOnlyLetters(String s) {
        char[] chs = s.toCharArray();
        int len = chs.length, l = 0, r = len - 1;
        while (l < r) {
            while (l < r && !Character.isLetter(chs[l])) { l++; }
            while (l < r && !Character.isLetter(chs[r])) { r--; }
            char ch = chs[l];
            chs[l++] = chs[r];
            chs[r--] = ch;
        }
        return new String(chs);
    }
}
