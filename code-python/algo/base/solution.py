from abc import ABCMeta, abstractmethod
from typing import List
import json


class Solution(metaclass=ABCMeta):
  @abstractmethod
  def test(self):
    '''
    在此处编写测试逻辑
    '''
    pass

  def stringToIntegerList(self, input):
    '''
    字符串转 int list
    '''
    return json.loads(input)

  def integerListToString(self, nums, len_of_list=None):
    '''
    int list 转字符串
    '''
    if not len_of_list:
      len_of_list = len(nums)
    return json.dumps(nums[:len_of_list])

  def stringToTreeNode(self, input):
    '''
    字符串转二叉树
    '''
    input = input.strip()
    input = input[1:-1]
    if not input:
      return None

    inputValues = [s.strip() for s in input.split(',')]
    root = TreeNode(int(inputValues[0]))
    nodeQueue = [root]
    front = 0
    index = 1
    while index < len(inputValues):
      node = nodeQueue[front]
      front = front + 1

      item = inputValues[index]
      index = index + 1
      if item != "null":
        leftNumber = int(item)
        node.left = TreeNode(leftNumber)
        nodeQueue.append(node.left)

      if index >= len(inputValues):
        break

      item = inputValues[index]
      index = index + 1
      if item != "null":
        rightNumber = int(item)
        node.right = TreeNode(rightNumber)
        nodeQueue.append(node.right)
    return root

  def int2dArrayToString(self, input):
    return json.dumps(input)


class TreeNode(object):
  def __init__(self, val=0, left=None, right=None):
    self.val = val
    self.left = left
    self.right = right
