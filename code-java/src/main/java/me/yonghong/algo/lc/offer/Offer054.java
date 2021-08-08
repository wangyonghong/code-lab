package me.yonghong.algo.lc.offer;

import me.yonghong.algo.Solution;

/**
 * 剑指 Offer 54. 二叉搜索树的第k大节点
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/"></a>
 * @since 2021/8/8
 **/
public class Offer054 implements Solution {

    private int res, k;

    public static void main(String[] args) {
        new Offer054().test();
    }

    @Override
    public void test() {
        TreeNode root = stringToTreeNode("[3,1,4,null,2]");
        System.out.println(kthLargest(root, 1));
        System.out.println(kthLargest(root, 2));
        System.out.println(kthLargest(root, 3));
        System.out.println(kthLargest(root, 4));
    }

    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return res;
    }

    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.right);
        if (k == 0) {
            return;
        }
        k--;
        if (k == 0) {
            res = root.val;
        }
        dfs(root.left);
    }
}
