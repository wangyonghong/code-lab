package me.yonghong.algo.lc.lc0201;

import me.yonghong.algo.Solution;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 224. 基本计算器
 * 224. Basic Calculator
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode-cn.com/problems/basic-calculator/"></a>
 * @link <a href="https://leetcode.com/problems/basic-calculator/"></a>
 * @link <a href="https://github.com/labuladong/fucking-algorithm/blob/master/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E7%B3%BB%E5%88%97/%E5%AE%9E%E7%8E%B0%E8%AE%A1%E7%AE%97%E5%99%A8.md"></a>
 * @see me.yonghong.algo.lc.lc0201.Lc0227
 * @see me.yonghong.algo.lc.lc0701.Lc0772
 * @since 2021/8/10
 **/
public class Lc0224 implements Solution {

    private int index = 0;

    public static void main(String[] args) {
        new Lc0224().test();
    }

    @Override
    public void test() {
        index = 0;
        System.out.println(calculate("1 + 1"));
        index = 0;
        System.out.println(calculate(" 2-1 + 2 "));
        index = 0;
        System.out.println(calculate("(1+(4+5+2)-3)+(6+8)"));
    }

    public int calculate(String s) {
        char[] ch = s.toCharArray();
        return calculate(ch);
    }

    private int calculate(char[] ch) {
        Deque<Integer> stack = new ArrayDeque<>();
        int num = 0;
        char sign = '+';
        for (; index < ch.length; index++) {
            char c = ch[index];
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            if (c == '(') {
                index++;
                num = calculate(ch);
            }
            if (!Character.isDigit(c) && c != ' ' || index == ch.length - 1) {
                int pre = 0;
                switch (sign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        pre = stack.pop();
                        stack.push(pre * num);
                        break;
                    case '/':
                        pre = stack.pop();
                        stack.push(pre / num);
                        break;
                }
                sign = c;
                num = 0;
            }
            if (c == ')') {
                break;
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
}
