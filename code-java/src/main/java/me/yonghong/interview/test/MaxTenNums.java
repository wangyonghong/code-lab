package me.yonghong.interview.test;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author yonghongwang#163.com
 * @since 2020/12/14
 **/
public class MaxTenNums {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 7, 8, 9, 0, 12, 12, 122, 33, 2, 2, 22, 2};
        test(nums);

    }

    public static int[] test(int[] nums) {
        Queue<Integer> heap = new PriorityQueue<>(10, (o1, o2) -> o1 - o2);
        for (int i = 0; i < 10; i++) {
            heap.offer(nums[i]);
        }
        for (int i = 10; i < nums.length; i++) {
            if (heap.peek() < nums[i]) {
                heap.poll();
                heap.offer(nums[i]);
            }
        }
        int[] res = new int[10];
        for (int i = 9; i >= 0; i--) {
            res[i] = heap.poll();
        }
        return res;
    }
}
