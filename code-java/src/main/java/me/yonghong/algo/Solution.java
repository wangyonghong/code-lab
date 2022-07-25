package me.yonghong.algo;

import me.yonghong.algo.lc.lc0001.Lc0001;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author yonghongwang#163.com
 * @since 2021/7/26
 */
public interface Solution extends Comparable<Solution> {

    public static void main(String[] args) {
        SolutionUtils.runTest(Lc0001.class);
    }

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
     * 打印 List<T> list
     *
     * @param list
     */
    default <T> void printList(List<T> list) {
        System.out.println(ArrayUtils.toString(list));
    }

    /**
     * 打印 List<List<T>>
     *
     * @param list
     */
    default <T> void printListList(List<List<T>> list) {
        for (List<T> l : list) {
            printList(l);
        }
    }

    /**
     * 打印链表
     *
     * @param head
     */
    default void print(ListNode head) {
        System.out.println(listNodeToString(head));
    }

    default int[] stringToIntegerArray(String input) {
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

    default ListNode stringToListNode(String input) {
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

    default String listNodeToString(ListNode node) {
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

    @Override
    default int compareTo(@NotNull Solution o) {
        return this.getClass().getCanonicalName().compareTo(o.getClass().getCanonicalName());
    }

    default TreeNode stringToTreeNode(String input) {
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

    class ListNode {
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

    class TreeNode {
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
