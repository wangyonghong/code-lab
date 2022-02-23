package me.yonghong.algo.lc.lc0101;

import me.yonghong.algo.Solution;

/**
 * 141. 环形链表
 * 141. Linked List Cycle
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode-cn.com/problems/linked-list-cycle/"></a>
 * @link <a href="https://leetcode.com/problems/linked-list-cycle/"></a>
 * @see me.yonghong.algo.lc.lc0401.Lc0457
 * @since 2021/8/7
 **/
class Lc0141 implements Solution {

    public static void main(String[] args) {

    }

    @Override
    public void test() {

    }

    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }
}
