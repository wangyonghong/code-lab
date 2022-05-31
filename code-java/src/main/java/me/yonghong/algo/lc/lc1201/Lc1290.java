package me.yonghong.algo.lc.lc1201;

import me.yonghong.algo.Solution;

/**
 * 1290. 二进制链表转整数
 * 1290. Convert Binary Number in a Linked List to Integer
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/convert-binary-number-in-a-linked-list-to-integer/"></a>
 * @link <a href="https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer/"></a>
 * @since 2021/8/19
 */
class Lc1290 implements Solution {

    public static void main(String[] args) {
        new Lc1290().test();
    }

    @Override
    public void test() {
        System.out.println(getDecimalValue(stringToListNode("[1,0,1]")));
        System.out.println(getDecimalValue(stringToListNode("[0]")));
        System.out.println(getDecimalValue(stringToListNode("[1]")));
        System.out.println(getDecimalValue(stringToListNode("[1,0,0,1,0,0,1,1,1,0,0,0,0,0,0]")));
    }

    public int getDecimalValue(ListNode head) {
        int res = 0;
        while (head != null) {
            res = res * 2 + head.val;
            head = head.next;
        }
        return res;
    }
}
