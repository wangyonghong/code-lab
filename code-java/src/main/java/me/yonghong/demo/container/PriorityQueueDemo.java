package me.yonghong.demo.container;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * @author yonghongwang#163.com
 * @since 2021/2/28
 */
public class PriorityQueueDemo {

    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        PriorityQueue<Integer> queue = buildMinHeap();
        offerRandomNum(queue, 5);
        pollAndPrint(queue);
        queue = buildMaxHeap();
        offerRandomNum(queue, 5);
        pollAndPrint(queue);
    }

    private static void offerRandomNum(PriorityQueue<Integer> queue, int k) {
        while (k-- != 0) {
            queue.offer(RANDOM.nextInt() % 10000);
        }
        System.out.println();
    }

    private static void pollAndPrint(PriorityQueue<Integer> queue) {
        while (queue.size() != 0) {
            System.out.print(queue.poll() + "\t");
        }
    }

    private static PriorityQueue<Integer> buildMinHeap() {
        // 默认小顶堆
        return new PriorityQueue<>();
    }

    private static PriorityQueue<Integer> buildMaxHeap() {
        return new PriorityQueue<>(((o1, o2) -> o2 - o1));
    }
}
