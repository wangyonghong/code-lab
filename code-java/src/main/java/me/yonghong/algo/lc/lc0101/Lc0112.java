package me.yonghong.algo.lc.lc0101;

import me.yonghong.algo.Solution;

/**
 * 112. 路径总和
 * 112. Path Sum
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode-cn.com/problems/path-sum/"></a>
 * @link <a href="https://leetcode.com/problems/path-sum/"></a>
 * @since 2021/8/19
 **/
class Lc0112 extends Solution {

    public static void main(String[] args) {
        new Lc0112().test();
    }

    @Override
    public void test() {
        TreeNode root;
        root = stringToTreeNode("[5,4,8,11,null,13,4,7,2,null,null,null,1]");
        System.out.println(hasPathSum(root, 22));
        root = stringToTreeNode("[1,2,3]");
        System.out.println(hasPathSum(root, 5));
        root = stringToTreeNode("[1,2]");
        System.out.println(hasPathSum(root, 0));
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && root.val == targetSum) {
            return true;
        }
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }
}
