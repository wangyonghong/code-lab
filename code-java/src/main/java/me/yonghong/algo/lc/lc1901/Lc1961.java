package me.yonghong.algo.lc.lc1901;

import me.yonghong.algo.Solution;

/**
 * 1961. 检查字符串是否为数组前缀
 * 1961. Check If String Is a Prefix of Array
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/check-if-string-is-a-prefix-of-array/"></a>
 * @link <a href="https://leetcode.com/problems/check-if-string-is-a-prefix-of-array/"></a>
 * @since 2021/8/12
 **/
class Lc1961 implements Solution {

    public static void main(String[] args) {
        new Lc1961().test();
    }

    @Override
    public void test() {
        System.out.println(isPrefixString("iloveleetcode", new String[]{"i", "love", "leetcode", "apples"}));
        System.out.println(isPrefixString("iloveleetcode", new String[]{"apples", "i", "love", "leetcode"}));
        System.out.println(isPrefixString("a", new String[]{"aa", "aaaa", "aaaaa"}));

    }

    public boolean isPrefixString(String s, String[] words) {
        int i = 0, j = 0, k = 0;
        while (i < s.length()) {
            if (j < words.length && s.charAt(i) == words[j].charAt(k)) {
                i++;
                k++;
                if (k == words[j].length()) {
                    j++;
                    k = 0;
                }
            } else {
                return false;
            }
        }
        return k == 0;
    }
}
