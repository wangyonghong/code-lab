package me.yonghong.algo.lc.lc0201;

import me.yonghong.algo.Solution;

/**
 * 236. 二叉树的最近公共祖先
 * 236. Lowest Common Ancestor of a Binary Tree
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/"></a>
 * @link <a href="https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/"></a>
 * @since 2021/7/28
 **/
class Lc0236 extends Solution {


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // if (left == null && right == null) return null; // 这种情况不存在
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
    }

}
