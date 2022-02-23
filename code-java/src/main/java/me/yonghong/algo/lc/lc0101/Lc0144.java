package me.yonghong.algo.lc.lc0101;

import me.yonghong.algo.Solution;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 144. 二叉树的前序遍历
 * 144. Binary Tree Preorder Traversal
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode-cn.com/problems/binary-tree-preorder-traversal/"></a>
 * @link <a href="https://leetcode.com/problems/binary-tree-preorder-traversal/"></a>
 * @since 2021/8/12
 **/
class Lc0144 implements Solution {

    public static void main(String[] args) {
        new Lc0144().test();
    }

    @Override
    public void test() {
        TreeNode root = stringToTreeNode("[1,null,2,3]");
        List<Integer> res = preorderTraversal(root);
        printList(res);
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        traversal(root, res);
        return res;
    }

    private void traversal(TreeNode root, List<Integer> res) {
        if (root != null) {
            res.add(root.val);
            traversal(root.left, res);
            traversal(root.right, res);
        }
    }

    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                res.add(root.val);
                stack.push(root);
                root = root.left;
            }
            TreeNode cur = stack.pop();
            root = cur.right;
        }
        return res;
    }
}
