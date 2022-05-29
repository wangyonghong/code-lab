package me.yonghong.algo.lc.lc0101;

import me.yonghong.algo.Solution;

/**
 * 104. 二叉树的最大深度
 * 104. Maximum Depth of Binary Tree
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/maximum-depth-of-binary-tree/"></a>
 * @link <a href="https://leetcode.com/problems/maximum-depth-of-binary-tree/"></a>
 * @since 2021/8/3
 **/
class Lc0104 extends Solution {

    public static void main(String[] args) {
        new Lc0104().test();
    }

    @Override
    public void test() {
        TreeNode root = stringToTreeNode("[3,9,20,null,null,15,7]");
        System.out.println(maxDepth(root));
    }

    public int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
