package me.yonghong.algo.lc.lc0001;

import me.yonghong.algo.Solution;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 94. 二叉树的中序遍历
 * 94. Binary Tree Inorder Traversal
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode-cn.com/problems/binary-tree-inorder-traversal/"></a>
 * @link <a href="https://leetcode.com/problems/binary-tree-inorder-traversal/"></a>
 * @since 2021/8/12
 **/
class Lc0094 implements Solution {

    public static void main(String[] args) {
        new Lc0094().test();
    }

    @Override
    public void test() {
        TreeNode root = stringToTreeNode("[1,null,2,3]");
        List<Integer> res = inorderTraversal(root);
        printList(res);
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        traversal(root, res);
        return res;
    }

    private void traversal(TreeNode root, List<Integer> res) {
        if (root != null) {
            traversal(root.left, res);
            res.add(root.val);
            traversal(root.right, res);
        }
    }

    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }
}
