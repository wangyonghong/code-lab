package me.yonghong.algo.lc.lc0301;

import me.yonghong.algo.Solution;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 345. 反转字符串中的元音字母
 * 345. Reverse Vowels of a String
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/reverse-vowels-of-a-string/"></a>
 * @link <a href="https://leetcode.com/problems/reverse-vowels-of-a-string/"></a>
 * @since 2021/8/18
 */
class Lc0345 implements Solution {

    public static void main(String[] args) {
        new Lc0345().test();
    }

    @Override
    public void test() {
        System.out.println(reverseVowels("hello"));
        System.out.println(reverseVowels("leetcode"));
    }

    private static final Set<Character> VOWELS = new HashSet<>() {{
        addAll(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
    }};

    public String reverseVowels(String s) {
        char[] chs = s.toCharArray();
        int len = chs.length, l = 0, r = len - 1;
        while (l < r) {
            while (l < r && !isVowel(chs[l])) {
                l++;
            }
            while (l < r && !isVowel(chs[r])) {
                r--;
            }
            char ch = chs[l];
            chs[l++] = chs[r];
            chs[r--] = ch;
        }
        return new String(chs);
    }

    private boolean isVowel(char ch) {
        return VOWELS.contains(ch);
    }
}
