package me.yonghong.interview.test;

/**
 * @author yonghongwang#163.com
 * @since 2020/12/10
 **/
public class ListTest {

    public static ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = new ListNode(-1);
        newHead.next = head;
        ListNode cur = newHead;
        while (cur.next != null && cur.next.next != null) {
            ListNode tmp1 = cur.next;
            ListNode tmp2 = tmp1.next;
            ListNode pre = tmp2.next;

            cur.next = tmp2;
            tmp2.next = tmp1;
            tmp1.next = pre;

            cur = tmp1;
        }
        return newHead.next;
    }

    public static void print(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.value);
            cur = cur.next;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode cur = head;
        cur.next = new ListNode(2);
        cur = cur.next;
        cur.next = new ListNode(3);
        cur = cur.next;
        cur.next = new ListNode(4);
        cur = cur.next;
        cur.next = new ListNode(5);
        cur = cur.next;

        ListNode newHead = reverse(head);

        print(newHead);
    }

    static class ListNode {
        Integer value;
        ListNode next;

        public ListNode(Integer value) {
            this.value = value;
        }
    }
}


