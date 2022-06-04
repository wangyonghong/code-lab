package me.yonghong.algo.lc.lc0801;

import me.yonghong.algo.Solution;
import me.yonghong.algo.SolutionUtils;

/**
 * 557. 反转字符串中的单词 III
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/reverse-words-in-a-string-iii/"></a>
 * @link <a href="https://leetcode.com/problems/reverse-words-in-a-string-iii/"></a>
 * @since 2022/6/1
 */
public interface Lc0876 extends Solution {

    public ListNode middleNode(ListNode head);

    @Override
    default void test() {
        ListNode input, res;
        input = stringToListNode("[1,2,3,4,5]");
        res = middleNode(input);
        print(res);
        input = stringToListNode("[1,2,3,4]");
        res = middleNode(input);
        print(res);
    }

    class Solution01 implements Lc0876 {

        @Override
        public ListNode middleNode(ListNode head) {
            ListNode s = head, f = head;
            while (f != null && f.next != null) {
                s = s.next;
                f = f.next.next;
            }
            return s;
        }
    }

    public static void main(String[] args) {
        SolutionUtils.runTest(Lc0876.class);
    }
}
