package me.yonghong.algo.lc.lc1901;

import me.yonghong.algo.Solution;

/**
 * 2000. 反转单词前缀
 * 2000. Reverse Prefix of Word
 * <p>
 * 给你一个下标从 0 开始的字符串 word 和一个字符 ch 。找出 ch 第一次出现的下标 i ，反转 word 中从下标 0 开始、直到下标 i
 * 结束（含下标 i ）的那段字符。如果 word 中不存在字符 ch ，则无需进行任何操作。
 * <p>
 * 例如，如果 word = "abcdefd" 且 ch = "d" ，那么你应该 反转 从下标 0 开始、直到下标 3 结束（含下标 3
 * ）。结果字符串将会是 "dcbaefd" 。
 * <p>
 * 返回 结果字符串 。
 * <p>
 * 示例 1：
 * 输入：word = "abcdefd", ch = "d"
 * 输出："dcbaefd"
 * 解释："d" 第一次出现在下标 3 。
 * 反转从下标 0 到下标 3（含下标 3）的这段字符，结果字符串是 "dcbaefd" 。
 * <p>
 * 示例 2：
 * 输入：word = "xyxzxe", ch = "z"
 * 输出："zxyxxe"
 * 解释："z" 第一次也是唯一一次出现是在下标 3 。
 * 反转从下标 0 到下标 3（含下标 3）的这段字符，结果字符串是 "zxyxxe" 。
 * <p>
 * 示例 3：
 * 输入：word = "abcd", ch = "z"
 * 输出："abcd"
 * 解释："z" 不存在于 word 中。
 * 无需执行反转操作，结果字符串是 "abcd" 。
 * <p>
 * 提示：
 * 1 <= word.length <= 250
 * word 由小写英文字母组成
 * ch 是一个小写英文字母
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/reverse-prefix-of-word/"></a>
 * @link <a href="https://leetcode.com/problems/reverse-prefix-of-word/"></a>
 * @since 2022/02/22
 **/
class Lc2000 extends Solution {

    public static void main(String[] args) {
        new Lc2000().test();
    }

    @Override
    public void test() {
        String res;
        res = reversePrefix("abcdefd", 'd');
        System.out.println(res);
        res = reversePrefix("xyxzxe", 'z');
        System.out.println(res);
        res = reversePrefix("abcd", 'z');
        System.out.println(res);
    }

    public String reversePrefix(String word, char ch) {
        int index = word.indexOf(ch);
        if (index >= 0) {
            char[] arr = word.toCharArray();
            int left = 0, right = index;
            while (left < right) {
                char temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
            word = new String(arr);
        }
        return word;
    }
}
