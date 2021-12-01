package me.yonghong.algo.lc.lc0101;

import me.yonghong.algo.Solution;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 102. 二叉树的层序遍历
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode-cn.com/problems/binary-tree-level-order-traversal/"></a>
 * @link <a href="https://leetcode.com/problems/binary-tree-level-order-traversal/"></a>
 * @since 2021/12/1
 **/
public class Lc0102 implements Solution {

    public static void main(String[] args) {
        new Lc0102().test();
    }

    @Override
    public void test() {
        System.out.println(levelOrder(stringToTreeNode("[3,9,20,null,null,15,7]")));
        System.out.println(levelOrderDFS1(stringToTreeNode("[3,9,20,null,null,15,7]")));
        System.out.println(levelOrderDFS2(stringToTreeNode("[3,9,20,null,null,15,7]")));
        System.out.println(levelOrder(stringToTreeNode("[]")));
        System.out.println(levelOrderDFS1(stringToTreeNode("[]")));
        System.out.println(levelOrderDFS2(stringToTreeNode("[]")));
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<List<Integer>> res = new ArrayList<>();

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.pop();
                currentLevel.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(currentLevel);
        }

        return res;
    }

    public List<List<Integer>> levelOrderDFS1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, res, 0);
        return res;
    }

    private void dfs(TreeNode root, List<List<Integer>> res, int level) {
        if (root == null) {
            return;
        }
        if (res.size() <= level) {
            res.add(new ArrayList<>());
        }
        res.get(level).add(root.val);
        dfs(root.left, res, level + 1);
        dfs(root.right, res, level + 1);
    }

    public List<List<Integer>> levelOrderDFS2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<Pair<TreeNode, Integer>> stack = new LinkedList<>();
        stack.push(new Pair<>(root, 0));
        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> nodePair = stack.pop();
            Integer level = nodePair.second;
            if (res.size() <= level) {
                res.add(new ArrayList<>());
            }
            TreeNode node = nodePair.first;
            res.get(level).add(node.val);
            if (node.right != null) {
                stack.push(new Pair<>(node.right, level + 1));
            }
            if (node.left != null) {
                stack.push(new Pair<>(node.left, level + 1));
            }
        }
        return res;
    }

    class Pair<F, S> {
        F first;
        S second;

        public Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }
    }
}
