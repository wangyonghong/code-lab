package me.yonghong.algo.lc.lc0201;

import me.yonghong.algo.Solution;

/**
 * 234. 回文链表
 * 234. Palindrome Linked List
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/palindrome-linked-list/"></a>
 * @link <a href="https://leetcode.com/problems/palindrome-linked-list/"></a>
 * @since 2021/8/4
 **/
class Lc0234 extends Solution {

    public static void main(String[] args) {
        new Lc0234().test();
    }

    @Override
    public void test() {
        ListNode head = stringToListNode("[1,2,2,1]");
        System.out.println(isPalindrome(head));
    }

    /**
     * 1. 放到数组中，双指针遍历
     * 2. 快慢指针，找到中点，反转后半部分，再次遍历
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode front = head;
        ListNode back = reverseList(slow);
        while (back != null) {
            if (front.val != back.val) {
                return false;
            }
            front = front.next;
            back = back.next;
        }
        return true;
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
