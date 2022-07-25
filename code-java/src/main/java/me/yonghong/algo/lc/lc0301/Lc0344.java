package me.yonghong.algo.lc.lc0301;

import me.yonghong.algo.Solution;

/**
 * 344. 反转字符串
 * 344. Reverse String
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/reverse-string/"></a>
 * @link <a href="https://leetcode.com/problems/reverse-string/"></a>
 * @since 2021/8/18
 */
class Lc0344 implements Solution {

    public static void main(String[] args) {
        new Lc0344().test();
    }

    @Override
    public void test() {
        char[] s = new char[]{'h', 'e', 'l', 'l', 'o'};
        reverseString(s);
        System.out.println(new String(s));
    }

    public void reverseString(char[] s) {
        int len = s.length, l = 0, r = len - 1;
        while (l < r) {
            char ch = s[l];
            s[l++] = s[r];
            s[r--] = ch;
        }
    }
}
