package me.yonghong.algo;

import java.util.Arrays;

/**
 * @author yonghongwang#163.com
 * @since 2021/7/26
 */
public interface Solution {

    /**
     * 测试用例
     */
    default void test() {
    }

    /**
     * 比较大小
     *
     * @param i
     * @param j
     * @return i < j
     */
    default boolean less(int i, int j) {
        return i < j;
    }

    /**
     * 交换位置
     *
     * @param nums
     * @param i
     * @param j
     */
    default void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    /**
     * 打印数组
     *
     * @param nums
     */
    default void print(int[] nums) {
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 打印链表
     *
     * @param head
     */
    default void print(ListNode head) {
        System.out.print("[");
        while (head != null) {
            if (head.next != null) {
                System.out.print(head.val + ", ");
            }
            head = head.next;
        }
        System.out.print("]");
    }

    class ListNode {
        public int val;
        public ListNode next;

        public ListNode() { }

        public ListNode(int val) { this.val = val; }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode() { }

        public TreeNode(int val) { this.val = val; }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
