package me.yonghong.algo.lc.lc0001;

import me.yonghong.algo.Solution;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 20. 有效的括号
 * 20. Valid Parentheses
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode-cn.com/problems/valid-parentheses/"></a>
 * @link <a href="https://leetcode.com/problems/valid-parentheses/"></a>
 * @since 2021/7/28
 **/
public class Lc0020 implements Solution {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        for (char ch : s.toCharArray()) {
            if (!stack.isEmpty() && map.get(ch) == stack.peek()) {
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }

    public boolean isValid2(String s) {
        int length = 0;
        while (s.length() > 0 && s.length() != length) {
            length = s.length();
            s = s
                    .replace("()", "")
                    .replace("[]", "")
                    .replace("{}", "");
        }
        return s.length() == 0;
    }

}
