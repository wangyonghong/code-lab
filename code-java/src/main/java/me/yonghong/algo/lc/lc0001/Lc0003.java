package me.yonghong.algo.lc.lc0001;

import me.yonghong.algo.Solution;
import me.yonghong.algo.SolutionUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * 3. 无重复字符的最长子串
 * 3. Longest Substring Without Repeating Characters
 * 标签：哈希表、双指针、字符串、滑动窗口
 * 难度：中等
 * <p>
 * 给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度。
 * <p>
 * 示例1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * <p>
 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 示例 4:
 * 输入: s = ""
 * 输出: 0
 * <p>
 * 提示：
 * 0 <= s.length <= 5 * 10^4
 * s 由英文字母、数字、符号和空格组成
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/longest-substring-without-repeating-characters/"></a>
 * @link <a href="https://leetcode.com/problems/longest-substring-without-repeating-characters/"></a>
 * @since 2022/02/24
 **/
public interface Lc0003 extends Solution {

    public static void main(String[] args) {
        SolutionUtils.runTest(Lc0003.class);
    }

    int lengthOfLongestSubstring(String s);

    @Override
    default void test() {
        int res;
        res = lengthOfLongestSubstring("abcabcbb");
        System.out.println(res);
        res = lengthOfLongestSubstring("bbbbb");
        System.out.println(res);
        res = lengthOfLongestSubstring("pwwkew");
        System.out.println(res);
        res = lengthOfLongestSubstring("");
        System.out.println(res);
    }


    class Solution1 implements Lc0003 {

        @Override
        public int lengthOfLongestSubstring(String s) {
            // 哈希集合，记录每个字符是否出现过
            Set<Character> occ = new HashSet<>();
            int n = s.length();
            // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
            int j = -1, ans = 0;
            for (int i = 0; i < n; i++) {
                if (i != 0) {
                    // 左指针向右移动一格，移除一个字符
                    occ.remove(s.charAt(i - 1));
                }
                while (j + 1 < n && !occ.contains(s.charAt(j + 1))) {
                    // 不断地移动右指针
                    occ.add(s.charAt(j + 1));
                    j++;
                }
                // 第 i 到 j 个字符是一个极长的无重复字符子串
                ans = Math.max(ans, j - i + 1);
            }
            return ans;
        }
    }
}
