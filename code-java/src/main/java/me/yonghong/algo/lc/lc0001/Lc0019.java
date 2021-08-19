package me.yonghong.algo.lc.lc0001;

import me.yonghong.algo.Solution;

/**
 * 19. 删除链表的倒数第 N 个结点
 * 19. Remove Nth Node From End of List
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/"></a>
 * @link <a href="https://leetcode.com/problems/remove-nth-node-from-end-of-list/"></a>
 * @since 2021/8/16
 **/
public class Lc0019 implements Solution {

    public static void main(String[] args) {
        new Lc0019().test();
    }

    @Override
    public void test() {
        ListNode head;
        ListNode res;
        head = stringToListNode("[1,2,3,4,5]");
        res = removeNthFromEnd(head, 2);
        print(res);
        head = stringToListNode("[1]");
        res = removeNthFromEnd(head, 1);
        print(res);
        head = stringToListNode("[1,2]");
        res = removeNthFromEnd(head, 1);
        print(res);
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyHead = new ListNode(-1, head);
        ListNode fast = head;
        ListNode slow = dummyHead;
        while (n > 0) {
            fast = fast.next;
            n--;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummyHead.next;
    }
}
