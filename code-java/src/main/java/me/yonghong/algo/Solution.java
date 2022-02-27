package me.yonghong.algo;

import org.apache.commons.lang3.ArrayUtils;

import java.util.*;

/**
 * @author yonghongwang#163.com
 * @since 2021/7/26
 */
public class Solution {

    private static final List<Solution> solutions = new ArrayList<>();

    public Solution register(Solution solution) {
        solutions.add(solution);
        return this;
    }

    /**
     * 测试用例
     */
    public void test() {
        solutions.forEach(solution -> {
            System.out.println(solution.getClass().getCanonicalName());
            solution.test();
        });
    }

    /**
     * 比较大小
     *
     * @param i
     * @param j
     * @return i < j
     */
    public boolean less(int i, int j) {
        return i < j;
    }

    /**
     * 交换位置
     *
     * @param nums
     * @param i
     * @param j
     */
    public void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    /**
     * 打印数组
     *
     * @param nums
     */
    public void print(int[] nums) {
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 打印 List<T> list
     *
     * @param list
     */
    public <T> void printList(List<T> list) {
        System.out.println(ArrayUtils.toString(list));
    }

    /**
     * 打印 List<List<T>>
     *
     * @param list
     */
    public <T> void printListList(List<List<T>> list) {
        for (List<T> l : list) {
            printList(l);
        }
    }

    /**
     * 打印链表
     *
     * @param head
     */
    public void print(ListNode head) {
        System.out.println(listNodeToString(head));
    }

    public int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for (int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);

        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for (int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    public String listNodeToString(ListNode node) {
        if (node == null) {
            return "[]";
        }

        String result = "";
        while (node != null) {
            result += Integer.toString(node.val) + ", ";
            node = node.next;
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
