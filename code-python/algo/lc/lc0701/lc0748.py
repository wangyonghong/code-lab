import os
import sys
import collections
from typing import Counter, List

sys.path.append(os.path.dirname(os.path.dirname(sys.path[0])))
from base.solution import Solution, TreeNode


class Lc0748(Solution):

	def shortestCompletingWord(self, licensePlate: str, words: List[str]) -> str:
		cnt = Counter(ch.lower() for ch in licensePlate if ch.isalpha())
		return min((word for word in words if not cnt - Counter(word)), key=len)

	def test(self):
		res = self.shortestCompletingWord("1s3 PSt", ["step", "steps", "stripe", "stepple"])
		print(res)


def main():
  Lc0748().test()


if __name__ == '__main__':
  main()
