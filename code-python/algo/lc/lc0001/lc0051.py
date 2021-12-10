import json
import os
import sys
from typing import List

sys.path.append(os.path.dirname(os.path.dirname(sys.path[0])))
from base.solution import Solution

# 51. N 皇后
# https://leetcode-cn.com/problems/n-queens/
# https://leetcode.com/problems/n-queens/


class Lc0051(Solution):
  result = []
  cols = set()
  pie = set()
  na = set()

  ################################################################################

  def solveNQueens(self, n):
    """
    :type n: int
    :rtype: List[List[str]]
    """
    self.result = []
    self.cols = set()
    self.pie = set()
    self.na = set()
    if n < 1:
      return
    self.DFS(n, 0, [])
    return self._generate_result(n)

  def DFS(self, n, row, cur_state):
    if row >= n:
      self.result.append(cur_state)
      return
    for col in range(n):
      if col in self.cols or row + col in self.pie or row - col in self.na:
        continue
      self.cols.add(col)
      self.pie.add(row + col)
      self.na.add(row - col)

      self.DFS(n, row + 1, cur_state + [col])

      self.cols.remove(col)
      self.pie.remove(row + col)
      self.na.remove(row - col)

  def _generate_result(self, n):
    board = []
    for res in self.result:
      for i in res:
        board.append("." * i + "Q" + "." * (n - i - 1))
    return [board[i: i + n] for i in range(0, len(board), n)]

  ################################################################################

  def solveNQueens2(self, n):
    def DFS(queens, na, pie):
      p = len(queens)
      if p == n:
        result.append(queens)
        return
      for q in range(n):
        if q not in queens and p - q not in na and p + q not in pie:
          DFS(queens + [q], na + [p - q], pie + [p + q])
    result = []
    DFS([], [], [])
    return [["." * i + "Q" + "." * (n - i - 1) for i in sol] for sol in result]

  ################################################################################

  def test(self):
    ret = self.solveNQueens(1)
    print(json.dumps(ret))
    ret = self.solveNQueens2(1)
    print(json.dumps(ret))
    ret = self.solveNQueens(4)
    print(json.dumps(ret))
    ret = self.solveNQueens2(4)
    print(json.dumps(ret))


def main():
  Lc0051().test()


if __name__ == '__main__':
  main()
