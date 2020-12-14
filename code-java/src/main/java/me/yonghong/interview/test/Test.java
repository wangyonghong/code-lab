package me.yonghong.interview.test;

/**
 * @author yonghongwang#163.com
 * @since 2020/12/11
 **/
public class Test {

    public ListNode fun2(ListNode head1, ListNode head2) {
        ListNode newHead = new ListNode(-1);
        ListNode curr = newHead;
        while (head1 != null || head2 != null) {
            if (head1 == null) {
                curr.next = head2;
                head2 = head2.next;
            } else if (head2 == null) {
                curr.next = head1;
                head1 = head1.next;
            } else {
                if (head1.value <= head2.value) {
                    curr.next = head1;
                    head1 = head1.next;
                } else {
                    curr.next = head2;
                    head2 = head2.next;
                }
            }
            curr = curr.next;
        }
        return newHead.next;
    }


    public int fun(int[] nums, int target) {
        int l = 0, r = nums.length;
        while (l <= r) {
            int mid = l + (r - l) >> 2;
            if (nums[mid] >= target) {
                while (nums[mid] >= target) {
                    mid--;
                }
                return mid;
            } else if (nums[mid] < target) {
                l = mid + 1;
            }
        }
        return -1;
    }

    static class ListNode {
        int value;
        ListNode next;

        public ListNode(int value) {
            this.value = value;
        }
    }
}


