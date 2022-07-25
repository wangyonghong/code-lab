package me.yonghong.algo.lc.lc1701;

import me.yonghong.algo.Solution;

import java.util.Stack;

/**
 * @author yonghongwang#163.com
 * @since 2021/4/18
 */
class Lc1740 implements Solution {

    public int findDistance(TreeNode root, int p, int q) {
        if (p == q) {
            return 0;
        }
        Stack<TreeNode> pathP = new Stack<>();
        findPath(pathP, root, p);
        Stack<TreeNode> pathQ = new Stack<>();
        findPath(pathQ, root, q);

        while (pathP.peek() == pathQ.peek()) {
            pathP.pop();
            pathQ.pop();
        }

        return pathP.size() + pathQ.size() - 1;
    }

    private boolean findPath(Stack<TreeNode> path, TreeNode root, int p) {
        if (root == null) {
            return false;
        }
        if (root.val == p) {
            path.push(root);
            return true;
        }
        boolean left = findPath(path, root.left, p);
        boolean right = findPath(path, root.right, p);
        if (left || right) {
            path.push(root);
        }
        return left || right;
    }

}
