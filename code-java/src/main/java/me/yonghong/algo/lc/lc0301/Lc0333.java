package me.yonghong.algo.lc.lc0301;

import me.yonghong.algo.Solution;

import java.util.HashMap;
import java.util.Map;

/**
 * 333. 最大 BST 子树
 * 333. Largest BST Subtree
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode-cn.com/problems/largest-bst-subtree/"></a>
 * @link <a href="https://leetcode.com/problems/largest-bst-subtree/"></a>
 * @since 2021/8/12
 **/
class Lc0333 extends Solution {

    public static void main(String[] args) {
        new Lc0333().test();
    }

    @Override
    public void test() {
        TreeNode root = stringToTreeNode("[4,2,7,2,3,5,null,2,null,null,null,null,null,1]");
        System.out.println(largestBSTSubtree(root));
    }

    public int largestBSTSubtree(TreeNode root) {
        Map<TreeNode, Triple> map = new HashMap<>();
        largestBSTSubtree(root, map);
        TreeNode largestBinaryTreeRoot = null;
        int max = 0;
        for (Map.Entry<TreeNode, Triple> entry : map.entrySet()) {
            if (entry.getValue().count > max) {
                largestBinaryTreeRoot = entry.getKey();
                max = entry.getValue().count;
            }
        }
        return max;
    }

    private void largestBSTSubtree(TreeNode root, Map<TreeNode, Triple> map) {
        if (root == null) {
            return;
        }
        largestBSTSubtree(root.left, map);
        largestBSTSubtree(root.right, map);
        if (root.left == null && root.right == null) {
            map.put(root, new Triple(1, root.val, root.val));
        }
        if (root.left != null && root.right == null) {
            if (map.get(root.left) != null && map.get(root.left).max < root.val) {
                map.put(root, new Triple(1 + map.get(root.left).count,
                        map.get(root.left).min, root.val));
            }
        }
        if (root.left == null && root.right != null) {
            if (map.get(root.right) != null && root.val < map.get(root.right).min) {
                map.put(root, new Triple(1 + map.get(root.right).count,
                        root.val, map.get(root.right).max));
            }
        }
        if (root.left != null && root.right != null) {
            if (map.get(root.left) != null && map.get(root.right) != null
                    && map.get(root.left).max < root.val && root.val < map.get(root.right).min) {
                map.put(root, new Triple(1 + map.get(root.left).count + map.get(root.right).count,
                        map.get(root.left).min, map.get(root.right).max));
            }
        }
    }


    class Triple {
        int count;
        int min;
        int max;

        public Triple(int count, int min, int max) {
            this.count = count;
            this.min = min;
            this.max = max;
        }
    }
}
