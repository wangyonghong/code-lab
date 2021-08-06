package me.yonghong.algo.other;

import java.util.HashMap;

/**
 * LRU Cache
 * 双向链表和HashMap
 *
 * @author yonghongwang#163.com
 * @since 2021/8/5
 **/
public class LRUCache {

    private final HashMap<Integer, Node> map;
    private final int limit;
    private final Node head;
    private final Node end;

    public LRUCache(int limit) {
        this.map = new HashMap<>();
        this.limit = limit;
        this.head = new Node(-1, -1);
        this.end = new Node(-1, -1);
        this.head.next = this.end;
        this.end.prev = this.head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node != null) {
            remove(node);
            addFirst(node);
            return node.value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node != null) {
            remove(node);
            node.value = value;
            addFirst(node);
        }
        if (node == null) {
            node = new Node(key, value);
            addFirst(node);
        }
        if (map.size() > limit) {
            removeLast();
        }
    }

    private void remove(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        node.prev = null;
        node.next = null;
        prev.next = next;
        next.prev = prev;
        map.remove(node.key);
    }

    private void addFirst(Node node) {
        Node second = head.next;
        head.next = node;
        node.prev = head;
        node.next = second;
        second.prev = node;
        map.put(node.key, node);
    }

    private void removeLast() {
        Node last = end.prev;
        Node secondLast = last.prev;
        last.prev = null;
        last.next = null;
        secondLast.next = end;
        end.prev = secondLast;
        map.remove(last.key);
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));
        lruCache.put(3, 3);
        System.out.println(lruCache.get(2));
        lruCache.put(4, 4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
    }

    static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node() {
        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
