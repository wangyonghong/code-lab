package me.yonghong.algo.lc.lc0101;

import me.yonghong.algo.Solution;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * 145. 二叉树的后序遍历
 * 145. Binary Tree Postorder Traversal
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/binary-tree-postorder-traversal/"></a>
 * @link <a href="https://leetcode.com/problems/binary-tree-postorder-traversal/"></a>
 * @since 2021/8/12
 **/
class Lc0145 implements Solution {

    public static void main(String[] args) {
        new Lc0145().test();
    }

    @Override
    public void test() {
        TreeNode root = stringToTreeNode("[1,null,2,3]");
        List<Integer> res = postorderTraversal(root);
        printList(res);
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        traversal(root, res);
        return res;
    }

    private void traversal(TreeNode root, List<Integer> res) {
        if (root != null) {
            traversal(root.left, res);
            traversal(root.right, res);
            res.add(root.val);
        }
    }

    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                res.add(root.val);
                stack.push(root);
                root = root.right;
            }
            TreeNode cur = stack.pop();
            root = cur.left;
        }
        Collections.reverse(res);
        return res;
    }
}
