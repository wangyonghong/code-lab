import os
import sys
from typing import List

sys.path.append(os.path.dirname(os.path.dirname(sys.path[0])))
from base.solution import Solution


class Lc0001(Solution):
  def twoSum(self, nums: List[int], target: int) -> List[int]:
    n = len(nums)
    for i in range(n):
      for j in range(i + 1, n):
        if nums[i] + nums[j] == target:
          return [i, j]
    return []

  def test(self):
    ret = self.twoSum(self.stringToIntegerList('[2,7,11,15]'), 9)
    print(self.integerListToString(ret))


def main():
  Lc0001().test()


if __name__ == '__main__':
  main()
