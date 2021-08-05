package me.yonghong.algo.lc.lc0201;

import me.yonghong.algo.Solution;

/**
 * 206. 反转链表
 * 206. Reverse Linked List
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode-cn.com/problems/reverse-linked-list/"></a>
 * @link <a href="https://leetcode.com/problems/reverse-linked-list/"></a>
 * @see me.yonghong.algo.lc.offer.Offer024
 * @since 2021/8/4
 **/
public class Lc0206 implements Solution {

    public static void main(String[] args) {
        new Lc0206().test();
    }

    @Override
    public void test() {
        ListNode head = stringToListNode("[1,2,3,4,5]");
        ListNode node = reverseList(head);
        print(node);
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }
}
