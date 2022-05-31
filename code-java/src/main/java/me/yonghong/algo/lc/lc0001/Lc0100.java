package me.yonghong.algo.lc.lc0001;

import me.yonghong.algo.Solution;

/**
 * 100. 相同的树
 * 100. Same Tree
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/same-tree/"></a>
 * @link <a href="https://leetcode.com/problems/same-tree/"></a>
 * @since 2021/8/2
 **/
class Lc0100 implements Solution {

    public static void main(String[] args) {
        new Lc0100().test();
    }

    @Override
    public void test() {
        TreeNode p;
        TreeNode q;
        p = stringToTreeNode("[1,2,3]");
        q = stringToTreeNode("[1,2,3]");
        System.out.println(isSameTree(p, q));
        p = stringToTreeNode("[1,2]");
        q = stringToTreeNode("[1,null,2]");
        System.out.println(isSameTree(p, q));
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
