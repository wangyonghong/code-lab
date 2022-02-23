package me.yonghong.algo.lc.lc0001;

import me.yonghong.algo.Solution;

/**
 * 9. 回文数
 * 9. Palindrome Number
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode-cn.com/problems/palindrome-number/"></a>
 * @link <a href="https://leetcode.com/problems/palindrome-number/"></a>
 * @since 2021/8/11
 **/
class Lc0009 implements Solution {

    public static void main(String[] args) {
        new Lc0009().test();
    }

    @Override
    public void test() {
        System.out.println(isPalindrome(-1));
        System.out.println(isPalindrome(0));
        System.out.println(isPalindrome(1));
        System.out.println(isPalindrome(10));
        System.out.println(isPalindrome(21120));
        System.out.println(isPalindrome(1234554321));
        System.out.println(isPalindrome(2134554312));
    }

    public boolean isPalindrome(int x) {
        if (x == 0) {
            return true;
        }
        if (x < 0 || x % 10 == 0) {
            return false;
        }
        int num = x, reverse = 0;
        while (num != 0) {
            int temp = num % 10;
            reverse = reverse * 10 + temp;
            num = (num - temp) / 10;
            if (reverse == num) {
                return true;
            }
        }
        return reverse == x;
    }
}
