package me.yonghong.algo.lc.lc0101;

import me.yonghong.algo.Solution;

/**
 * 125. 验证回文串
 * 125. Valid Palindrome
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/valid-palindrome/"></a>
 * @link <a href="https://leetcode.com/problems/valid-palindrome/"></a>
 * @since 2021/8/19
 **/
class Lc0125 implements Solution {

    public static void main(String[] args) {
        new Lc0125().test();
    }

    @Override
    public void test() {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(isPalindrome("race a car"));
    }

    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        int l = 0, r = s.length() - 1;
        while (l < r) {
            while (l < r && !Character.isLetterOrDigit(s.charAt(l))) {
                l++;
            }
            while (l < r && !Character.isLetterOrDigit(s.charAt(r))) {
                r--;
            }
            if (s.charAt(l++) != s.charAt(r--)) {
                return false;
            }
        }
        return true;
    }
}
