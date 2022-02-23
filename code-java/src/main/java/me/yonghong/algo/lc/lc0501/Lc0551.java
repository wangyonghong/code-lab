package me.yonghong.algo.lc.lc0501;

import me.yonghong.algo.Solution;

/**
 * 551. 学生出勤记录 I
 * 551. Student Attendance Record I
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode-cn.com/problems/student-attendance-record-i/"></a>
 * @link <a href="https://leetcode.com/problems/student-attendance-record-i/"></a>
 * @since 2021/8/17
 */
class Lc0551 implements Solution {

    public static void main(String[] args) {
        new Lc0551().test();
    }

    @Override
    public void test() {
        Solution.super.test();
    }

    public boolean checkRecord(String s) {
        int absent = 0;
        int late = 0;
        for (char ch : s.toCharArray()) {
            if (ch == 'A') {
                absent++;
                late = 0;
            } else if (ch == 'L') {
                late++;
            } else if (ch == 'P') {
                late = 0;
            }
            if (absent >= 2 || late >= 3) {
                return false;
            }
        }
        return true;
    }
}
