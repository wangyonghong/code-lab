package me.yonghong.algo.lc.lc0501;

import me.yonghong.algo.Solution;

/**
 * 541. 反转字符串 II
 * 541. Reverse String II
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/reverse-string-ii/"></a>
 * @link <a href="https://leetcode.com/problems/reverse-string-ii/"></a>
 * @since 2021/8/18
 */
class Lc0541 extends Solution {

    public static void main(String[] args) {
        new Lc0541().test();
    }

    @Override
    public void test() {
        System.out.println(reverseStr("abcdefg", 2));
        System.out.println(reverseStr("abcd", 2));
    }

    public String reverseStr(String s, int k) {
        char[] chs = s.toCharArray();
        int len = chs.length, l = 0, r = Math.min(len - 1, l + k - 1), cur = 0;
        while(cur < len) {
            while (l < r) {
                char ch = chs[l];
                chs[l++] = chs[r];
                chs[r--] = ch;
            }
            cur = cur + 2 * k;
            l = cur;
            r = Math.min(len - 1, l + k - 1);
        }
        return new String(chs);
    }
}
