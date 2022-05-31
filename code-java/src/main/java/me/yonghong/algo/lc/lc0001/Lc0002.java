package me.yonghong.algo.lc.lc0001;

import me.yonghong.algo.Solution;
import me.yonghong.algo.SolutionUtils;

/**
 * 2. 两数相加
 * 2. Add Two Numbers
 * 标签：链表
 * 难度：中等
 * <p>
 * 给你两个非空的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例 1：
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * <p>
 * 示例 2：
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * <p>
 * 示例 3：
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 * <p>
 * 提示：
 * 每个链表中的节点数在范围 [1, 100] 内
 * 0 <= Node.val <= 9
 * 题目数据保证列表表示的数字不含前导零
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/add-two-numbers"></a>
 * @link <a href="https://leetcode.com/problems/add-two-numbers"></a>
 * @since 2021/2/21
 **/
interface Lc0002 extends Solution {

    public static void main(String[] args) {
        SolutionUtils.runTest(Lc0002.class);
    }

    ListNode addTwoNumbers(ListNode l1, ListNode l2);

    @Override
    default void test() {
        // case 1
        ListNode l1, l2, res;
        l1 = stringToListNode("[2,4,3]");
        l2 = stringToListNode("[5,6,4]");
        res = addTwoNumbers(l1, l2);
        print(res);

        // case 2
        l1 = stringToListNode("[2,4,3]");
        l2 = stringToListNode("[5,6,4]");
        res = addTwoNumbers(l1, l2);
        print(res);
    }


    class Solution1 implements Lc0002 {

        @Override
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode head = new ListNode();
            ListNode curr = head;
            int carry = 0, sum = 0;
            while (l1 != null || l2 != null) {
                if (l1 != null && l2 != null) {
                    sum = l1.val + l2.val + carry;
                    l1 = l1.next;
                    l2 = l2.next;
                } else if (l1 != null) {
                    sum = l1.val + carry;
                    l1 = l1.next;
                } else if (l2 != null) {
                    sum = l2.val + carry;
                    l2 = l2.next;
                }
                curr.next = new ListNode(sum % 10);
                curr = curr.next;
                carry = sum / 10;
            }
            if (carry == 1) {
                curr.next = new ListNode(1);
            }
            return head.next;
        }
    }

    class Solution2 implements Lc0002 {

        @Override
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            if (l1 == null) {
                return l2;
            }
            if (l2 == null) {
                return l1;
            }
            int val = l1.val + l2.val;
            ListNode next = addTwoNumbers(l1.next, l2.next);
            if (val >= 10) {
                val -= 10;
                next = addTwoNumbers(next, new ListNode(1));
            }
            return new ListNode(val, next);
        }
    }
}

