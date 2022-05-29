package me.yonghong.algo.lc.lc0201;

import me.yonghong.algo.Solution;

/**
 * 208. 实现 Trie (前缀树)
 * 208. Implement Trie (Prefix Tree)
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/implement-trie-prefix-tree/"></a>
 * @link <a href="https://leetcode.com/problems/implement-trie-prefix-tree/"></a>
 * @since 2021/8/19
 **/
class Lc0208 extends Solution {

    public static void main(String[] args) {
        new Lc0208().test();
    }

    @Override
    public void test() {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));
        trie.insert("app");
        System.out.println(trie.search("app"));
    }

    class Trie {

        private Trie[] children;
        private boolean isEnd;

        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }

        public void insert(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                int index = ch - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new Trie();
                }
                node = node.children[index];
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            Trie node = searchPrefix(word);
            return node != null && node.isEnd;
        }

        public boolean startsWith(String prefix) {
            return searchPrefix(prefix) != null;
        }

        private Trie searchPrefix(String prefix) {
            Trie node = this;
            for (int i = 0; i < prefix.length(); i++) {
                char ch = prefix.charAt(i);
                int index = ch - 'a';
                if (node.children[index] == null) {
                    return null;
                }
                node = node.children[index];
            }
            return node;
        }
    }
}
