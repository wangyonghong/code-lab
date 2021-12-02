import os
import sys
import collections
from typing import List

sys.path.append(os.path.dirname(os.path.dirname(sys.path[0])))
from base.solution import Solution, TreeNode

# 102. 二叉树的层序遍历
# https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
# https://leetcode.com/problems/binary-tree-level-order-traversal/


class Lc0101(Solution):

  def levelOrder(self, root: TreeNode) -> List[List[int]]:
    # BFS 广度优先
    if not root:
      return []

    result = []
    queue = collections.deque()
    queue.append(root)

    while queue:
      level_size = len(queue)
      current_level = []
      for _ in range(level_size):
        node = queue.popleft()
        current_level.append(node.val)
        if node.left:
          queue.append(node.left)
        if node.right:
          queue.append(node.right)
      result.append(current_level)
    return result

  def levelOrderDFS1(self, root):
    # DFS 深度优先
    result = []
    self.dfs(root, result, 0)
    return result

  def dfs(self, root, result, level):
    if not root:
      return
    if len(result) <= level:
      result.append([])
    result[level].append(root.val)
    self.dfs(root.left, result, level + 1)
    self.dfs(root.right, result, level + 1)

  def levelOrderDFS2(self, root):
    # DFS 深度优先
    result = []
    if not root:
      return result

    stack = collections.deque()
    stack.append([root, 0])

    while stack:
      node_pair = stack.pop()
      node = node_pair[0]
      level = node_pair[1]
      if len(result) <= level:
        result.append([])
      result[level].append(node.val)
      if node.right:
        stack.append([node.right, level + 1])
      if node.left:
        stack.append([node.left, level + 1])
    return result

  def test(self):
    ret = self.levelOrder(self.stringToTreeNode('[3,9,20,null,null,15,7]'))
    print(self.int2dArrayToString(ret))
    ret = self.levelOrderDFS1(self.stringToTreeNode('[3,9,20,null,null,15,7]'))
    print(self.int2dArrayToString(ret))
    ret = self.levelOrderDFS2(self.stringToTreeNode('[3,9,20,null,null,15,7]'))
    print(self.int2dArrayToString(ret))
    ret = self.levelOrder(self.stringToTreeNode('[]'))
    print(self.int2dArrayToString(ret))
    ret = self.levelOrderDFS1(self.stringToTreeNode('[]'))
    print(self.int2dArrayToString(ret))
    ret = self.levelOrderDFS2(self.stringToTreeNode('[]'))
    print(self.int2dArrayToString(ret))


def main():
  Lc0101().test()


if __name__ == '__main__':
  main()
