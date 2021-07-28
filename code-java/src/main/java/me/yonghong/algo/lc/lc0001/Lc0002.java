package me.yonghong.algo.lc.lc0001;

import me.yonghong.algo.Solution;

/**
 * @author yonghongwang#163.com
 * @link https://leetcode-cn.com/problems/add-two-numbers
 *
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * @since 2021/2/21
 **/
public class Lc0002 implements Solution {

    public static void main(String[] args) {
        new Lc0002().test();
    }

    @Override
    public void test() {
        ListNode curr;

        ListNode input1L1 = new ListNode(2);
        curr = input1L1;
        curr.next = new ListNode(4);
        curr = curr.next;
        curr.next = new ListNode(3);

        ListNode input1L2 = new ListNode(5);
        curr = input1L2;
        curr.next = new ListNode(6);
        curr = curr.next;
        curr.next = new ListNode(4);

        ListNode res1 = new Lc0002().addTwoNumbers(input1L1, input1L2);
        print(res1);
    }

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

