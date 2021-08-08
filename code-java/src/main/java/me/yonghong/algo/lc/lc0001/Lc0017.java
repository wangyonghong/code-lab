package me.yonghong.algo.lc.lc0001;

import me.yonghong.algo.Solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 17. 电话号码的字母组合
 * 17. Letter Combinations of a Phone Number
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/"></a>
 * @link <a href="https://leetcode.com/problems/letter-combinations-of-a-phone-number/"></a>
 * @since 2021/8/7
 **/
public class Lc0017 implements Solution {

    public static void main(String[] args) {
        new Lc0017().test();
    }

    @Override
    public void test() {
        List<String> list = letterCombinations("23");
        printList(list);
    }

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.length() == 0) {
            return res;
        }
        HashMap<Character, String> map = new HashMap<>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};

        StringBuilder path = new StringBuilder();
        dfs(map, digits, res, path, 0);
        return res;
    }

    private void dfs(HashMap<Character, String> map, String digits, List<String> res, StringBuilder path, int index) {
        if (path.length() == digits.length()) {
            res.add(path.toString());
            return;
        }
        String s = map.get(digits.charAt(index));
        for (int i = 0; i < s.length(); i++) {
            path.append(s.charAt(i));
            dfs(map, digits, res, path, index + 1);
            path.deleteCharAt(index);
        }
    }
}
