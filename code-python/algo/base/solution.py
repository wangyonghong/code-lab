from typing import List
import json

class Solution:
  def stringToIntegerList(self, input):
    return json.loads(input)

  def integerListToString(self, nums, len_of_list=None):
    if not len_of_list:
      len_of_list = len(nums)
    return json.dumps(nums[:len_of_list])