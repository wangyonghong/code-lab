package me.yonghong.algo.lc.offer;

import me.yonghong.algo.Solution;

import java.util.PriorityQueue;

/**
 * @author yonghongwang#163.com
 * @since 2021/8/4
 **/
public class Offer040 extends Solution {

    public static void main(String[] args) {
        new Offer040().test();
    }

    @Override
    public void test() {
        int[] numbers = getLeastNumbers(new int[]{4, 5, 1, 6, 2, 7, 3}, 4);
        print(numbers);
    }

    public int[] getLeastNumbers(int[] arr, int k) {
        // 大顶堆
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        for (int a : arr) {
            if (queue.size() == k && queue.peek() > a) {
                queue.poll();
            }
            queue.offer(a);
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = queue.poll();
        }
        return res;
    }
}
