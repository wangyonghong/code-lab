package me.yonghong.algo.lc.lc0001;

import me.yonghong.algo.Solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Lc0056 implements Solution {

    public static void main(String[] args) {
        new Lc0056().test();
    }

    @Override
    public void test() {
        int[][] res = merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}});
        print(res);
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        List<int[]> merged = new ArrayList<>();
        for (int[] interval : intervals) {
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
                merged.add(new int[]{interval[0], interval[1]});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], interval[1]);
            }
        }
        return merged.toArray(new int[merged.size()][2]);
    }
}
