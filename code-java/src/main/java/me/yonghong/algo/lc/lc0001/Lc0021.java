package me.yonghong.algo.lc.lc0001;

import me.yonghong.algo.Solution;

/**
 * 21. 合并两个有序链表
 * 21. Merge Two Sorted Lists
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/merge-two-sorted-lists/"></a>
 * @link <a href="https://leetcode.com/problems/merge-two-sorted-lists/"></a>
 * @since 2021/8/8
 **/
class Lc0021 implements Solution {

    public static void main(String[] args) {
        new Lc0021().test();
    }

    @Override
    public void test() {
        ListNode l1 = stringToListNode("[1,2,4]");
        ListNode l2 = stringToListNode("[1,3,4]");
        ListNode l3 = mergeTwoLists(l1, l2);
        print(l3);
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(-1);
        ListNode curr = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                ListNode temp = l1.next;
                l1.next = null;
                curr.next = l1;
                l1 = temp;
            } else {
                ListNode temp = l2.next;
                l2.next = null;
                curr.next = l2;
                l2 = temp;
            }
            curr = curr.next;
        }
        if (l1 == null) {
            curr.next = l2;
        } else {
            curr.next = l1;
        }
        return dummyHead.next;
    }
}
