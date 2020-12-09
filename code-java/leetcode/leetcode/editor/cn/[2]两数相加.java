//给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。 
//
// 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。 
//
// 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
//
// 示例： 
//
// 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//输出：7 -> 0 -> 8
//原因：342 + 465 = 807
// 
// Related Topics 链表 数学 
// 👍 5228 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p1 = l1, p2 = l2;
        ListNode last = null;
        int carry = 0, result = 0;
        boolean flag = true;
        while (p1 != null || p2 != null) {
            result = carry;
            if (p1 != null) {
                last = p1;
                flag = true;
                result += p1.val;
            }
            if (p2 != null) {
                last = p2;
                flag = false;
                result += p2.val;
                p2.val = result % 10;
                p2 = p2.next;
            }
            if (p1 != null) {
                p1.val = result % 10;
                p1 = p1.next;
            }
            carry = result / 10;
        }
        if (carry > 0) {
            last.next = new ListNode(carry);
        }
        return flag ? l1 : l2;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode p1 = l1, p2 = l2;
        ListNode curr = head;
        int carry = 0, result = 0;
        while (p1 != null || p2 != null) {
            result = carry; carry = 0;
            if (p1 != null) {
                result += p1.val;
                p1 = p1.next;
            }
            if (p2 != null) {
                result += p2.val;
                p2 = p2.next;
            }
            curr.next = new ListNode(result % 10);
            curr = curr.next;
            carry = result / 10;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return head.next;
    }
}