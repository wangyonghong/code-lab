package me.yonghong.algo.lc.lc0001;

import me.yonghong.algo.Solution;

/**
 * 4. 寻找两个正序数组的中位数
 * 4. Median of Two Sorted Arrays
 * 标签：数组、二分查找、分治
 * 难度：困难
 * <p>
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数 。
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 * <p>
 * 示例 1：
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * <p>
 * 示例 2：
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * <p>
 * 提示：
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -10^6 <= nums1[i], nums2[i] <= 10^6
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/median-of-two-sorted-arrays/"></a>
 * @link <a href="https://leetcode.com/problems/median-of-two-sorted-arrays/"></a>
 * @since 2022/02/27
 **/
public class Lc0004 extends Solution {

    public static void main(String[] args) {
        new Solution()
                .register(new Solution1())
                .register(new Solution2())
                .test();
    }

    @Override
    public void test() {
        double res;
        res = findMedianSortedArrays(new int[]{1, 3}, new int[]{2});
        System.out.println(res);

        res = findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4});
        System.out.println(res);
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return -1;
    }

    static class Solution1 extends Lc0004 {
        // 先合并，再取中位数，时间复杂度 O(m+n)
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int i = 0, j = 0, k = 0, m = nums1.length, n = nums2.length;
            int[] nums = new int[m + n];
            while (i < m || j < n) {
                if (i < m && j < n && nums1[i] < nums2[j]) {
                    nums[k++] = nums1[i++];
                } else if (i < m && j < n && nums1[i] >= nums2[j]) {
                    nums[k++] = nums2[j++];
                } else if (i < m && j >= n) {
                    nums[k++] = nums1[i++];
                } else if (i >= m) {
                    nums[k++] = nums2[j++];
                }
            }
            if ((m + n) % 2 == 0) {
                return (nums[(m + n) / 2] + nums[(m + n) / 2 - 1]) / 2.0;
            } else {
                return nums[(m + n) / 2];
            }
        }
    }

    static class Solution2 extends Lc0004 {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int m = nums1.length, n = nums2.length;
            int totalLength = m + n;
            if (totalLength % 2 == 1) {
                int midIndex = totalLength / 2;
                return getKthElement(nums1, nums2, midIndex + 1);
            } else {
                int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
                return (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
            }
        }

        public int getKthElement(int[] nums1, int[] nums2, int k) {
            /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
             * 这里的 "/" 表示整除
             * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
             * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
             * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
             * 这样 pivot 本身最大也只能是第 k-1 小的元素
             * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
             * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
             * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
             */
            int m = nums1.length, n = nums2.length;
            int index1 = 0, index2 = 0;

            while (true) {
                // 边界情况
                if (index1 == m) {
                    return nums2[index2 + k - 1];
                }
                if (index2 == n) {
                    return nums1[index1 + k - 1];
                }
                if (k == 1) {
                    return Math.min(nums1[index1], nums2[index2]);
                }

                // 正常情况
                int half = k / 2;
                int newIndex1 = Math.min(index1 + half, m) - 1;
                int newIndex2 = Math.min(index2 + half, n) - 1;
                int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
                if (pivot1 <= pivot2) {
                    k -= (newIndex1 - index1 + 1);
                    index1 = newIndex1 + 1;
                } else {
                    k -= (newIndex2 - index2 + 1);
                    index2 = newIndex2 + 1;
                }
            }
        }
    }
}
