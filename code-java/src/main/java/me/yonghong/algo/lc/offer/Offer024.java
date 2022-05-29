package me.yonghong.algo.lc.offer;

import me.yonghong.algo.Solution;


/**
 * 剑指 Offer 24. 反转链表
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/fan-zhuan-lian-biao-lcof/"></a>
 * @link <a href="https://leetcode.cn/problems/UHnkqh/"></a>
 * @see me.yonghong.algo.lc.lc0201.Lc0206
 * @since 2021/8/4
 **/
public class Offer024 extends Solution {

    public static void main(String[] args) {
        new Offer024().test();
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
