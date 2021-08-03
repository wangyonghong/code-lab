package me.yonghong.algo.lc.lc0101;

import me.yonghong.algo.Solution;

/**
 * 101. 对称二叉树
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode-cn.com/problems/symmetric-tree/"></a>
 * @link <a href="https://leetcode.com/problems/symmetric-tree/"></a>
 * @since 2021/8/2
 **/
public class Lc0101 implements Solution {

    public static void main(String[] args) {
        new Lc0101().test();
    }

    @Override
    public void test() {
        System.out.println(isSymmetric(stringToTreeNode("[1,2,2,3,4,4,3]")));
        System.out.println(isSymmetric(stringToTreeNode("[1,2,2,null,3,null,3]")));
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && isSymmetric(p.left, q.right) && isSymmetric(p.right, q.left);
    }
}
