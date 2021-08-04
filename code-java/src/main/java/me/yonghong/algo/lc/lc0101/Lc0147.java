package me.yonghong.algo.lc.lc0101;

import me.yonghong.algo.Solution;

/**
 * 147. 对链表进行插入排序
 * 147. Insertion Sort List
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode-cn.com/problems/insertion-sort-list/"></a>
 * @link <a href="https://leetcode.com/problems/insertion-sort-list/"></a>
 * @since 2021/8/4
 **/
public class Lc0147 implements Solution {

    public static void main(String[] args) {
        new Lc0147().test();
    }

    @Override
    public void test() {
        ListNode head = stringToListNode("[4,2,1,3]");
        ListNode newHead = insertionSortList(head);
        print(newHead);
    }

    public ListNode insertionSortList(ListNode head) {
        ListNode dummyHead = new ListNode(-1);
        ListNode curr = head;
        while (curr != null) {
            ListNode prev = dummyHead;
            ListNode next = curr.next;
            while (prev.next != null) {
                if (prev.next.val > curr.val) {
                    break;
                }
                prev = prev.next;
            }
            ListNode temp = prev.next;
            prev.next = curr;
            curr.next = temp;
            curr = next;
        }
        return dummyHead.next;
    }
}
