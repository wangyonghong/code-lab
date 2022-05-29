package me.yonghong.algo.lc.lc0101;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 146. LRU 缓存机制
 * https://leetcode.cn/problems/lru-cache/
 *
 * @author yonghongwang#163.com
 * @since 2021/7/28
 **/
class Lc0146 extends LinkedHashMap<Integer, Integer> {

    private int capacity;

    public Lc0146(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}
