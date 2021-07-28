package me.yonghong.algo.lc.lc0101;

import java.util.ArrayList;
import java.util.List;

import me.yonghong.algo.Solution;

/**
 * @author yonghongwang#163.com
 * @since 2021/4/18
 */
public class Lc0199 implements Solution {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) { return res; }
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
