package me.yonghong.algo.lc.lc0101;

import me.yonghong.algo.Solution;

import java.util.ArrayList;
import java.util.List;

/**
 * 199. 二叉树的右视图
 * 199. Binary Tree Right Side View
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode-cn.com/problems/binary-tree-right-side-view/"></a>
 * @link <a href="https://leetcode.com/problems/binary-tree-right-side-view/"></a>
 * @since 2021/4/18
 */
public class Lc0199 implements Solution {

    public static void main(String[] args) {
        new Lc0199().test();
    }

    @Override
    public void test() {
        TreeNode root = stringToTreeNode("[1,2,3,null,5,null,4]");
        List<Integer> list = rightSideView(root);
        printList(list);
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        ArrayList<TreeNode> q = new ArrayList<>();
        q.add(root);
        // 标志位
        int k = 0;
        while (k != q.size()) {
            int size = q.size() - k;
            for (int i = 0; i < size; i++) {
                TreeNode node = q.get(k++);
                if (i == size - 1) {
                    res.add(node.val);
                }
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
        }
        return res;
    }
}
