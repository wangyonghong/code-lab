package me.yonghong.algo.lc.lc0001;

import me.yonghong.algo.Solution;

/**
 * 67. 二进制求和
 * 67. Add Binary
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/add-binary/"></a>
 * @link <a href="https://leetcode.com/problems/add-binary/"></a>
 * @since 2021/8/8
 **/
class Lc0067 implements Solution {

    public static void main(String[] args) {
        new Lc0067().test();
    }

    @Override
    public void test() {
        System.out.println(addBinary("11", "1"));
        System.out.println(addBinary("1010", "1011"));
    }

    public String addBinary(String a, String b) {
        int i = a.length() - 1;
        int j = b.length() - 1;
        int temp = 0, curr = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while (i >= 0 || j >= 0 || temp > 0) {
            if (i >= 0 && j >= 0) {
                temp = temp + a.charAt(i) + b.charAt(j) - '0' - '0';
                i--;
                j--;
            } else if (i >= 0) {
                temp = temp + a.charAt(i) - '0';
                i--;
            } else if (j >= 0) {
                temp = temp + b.charAt(j) - '0';
                j--;
            }
            curr = temp % 2;
            stringBuilder.append(curr);
            temp = (temp - curr) / 2;
        }
        return stringBuilder.reverse().toString();
    }
}
