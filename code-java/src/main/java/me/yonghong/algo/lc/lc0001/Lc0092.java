package me.yonghong.algo.lc.lc0001;

import me.yonghong.algo.Solution;

/**
 * 92. 反转链表 II
 * 92. Reverse Linked List II
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/reverse-linked-list-ii/"></a>
 * @link <a href="https://leetcode.com/problems/reverse-linked-list-ii/"></a>
 * @since 2021/8/4
 **/
class Lc0092 extends Solution {

    public static void main(String[] args) {
        new Lc0092().test();
    }

    @Override
    public void test() {
        ListNode head;
        ListNode newHead;
        head = stringToListNode("[1,2,3,4,5]");
        newHead = reverseBetween(head, 2, 4);
        print(newHead);
        head = stringToListNode("[1,2,3,4,5]");
        newHead = reverseBetween(head, 1, 4);
        print(newHead);
        head = stringToListNode("[1,2,3,4,5]");
        newHead = reverseBetween(head, 2, 5);
        print(newHead);
        head = stringToListNode("[1,2,3,4,5]");
        newHead = reverseBetween(head, 1, 5);
        print(newHead);
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 因为头节点有可能发生变化，使用虚拟头节点可以避免复杂的分类讨论
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode curr = dummyNode, prev = null;
        for (int i = 0; i < left - 1; i++) {
            curr = curr.next;
        }
        ListNode leftNodePrev = curr;
        ListNode leftNode = curr.next;

        for (int i = left; i <= right; i++) {
            curr = curr.next;
        }

        ListNode rightNode = curr;
        ListNode rightNodeNext = curr.next;

        leftNodePrev.next = null;
        rightNode.next = null;

        reverseList(leftNode);

        leftNode.next = rightNodeNext;
        leftNodePrev.next = rightNode;
        return dummyNode.next;
    }

    public void reverseList(ListNode head) {
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
    }
}
