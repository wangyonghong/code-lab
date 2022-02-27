package me.yonghong.algo.lc.lc0101;

import me.yonghong.algo.Solution;

/**
 * 110. 平衡二叉树
 * 110. Balanced Binary Tree
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode-cn.com/problems/balanced-binary-tree/"></a>
 * @link <a href="https://leetcode.com/problems/balanced-binary-tree/"></a>
 * @since 2021/8/16
 **/
class Lc0110 extends Solution {

    public static void main(String[] args) {
        new Lc0110().test();
    }

    @Override
    public void test() {
        TreeNode root;
        root = stringToTreeNode("[3,9,20,null,null,15,7]");
        System.out.println(isBalanced(root));
        root = stringToTreeNode("[1,2,2,3,3,null,null,4,4]");
        System.out.println(isBalanced(root));
        root = stringToTreeNode("[]");
        System.out.println(isBalanced(root));
    }

    public boolean isBalanced(TreeNode root) {
        return height(root) >= 0;
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = height(root.left);
        if (left == -1) {
            return -1;
        }
        int right = height(root.right);
        if (right == -1) {
            return -1;
        }
        return Math.abs(left - right) > 1 ? -1 : Math.max(left, right) + 1;
    }


}
