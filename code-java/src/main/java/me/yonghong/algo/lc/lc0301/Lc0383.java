package me.yonghong.algo.lc.lc0301;

import me.yonghong.algo.Solution;

public class Lc0383 implements Solution {
    public static void main(String[] args) {
        new Lc0383().test();
    }

    @Override
    public void test() {
        assert !canConstruct("a", "b");
        assert !canConstruct("aa", "ab");
        assert canConstruct("aa", "aab");
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        int[] table = new int[26];
        for (char c : magazine.toCharArray()) {
            table[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            table[c - 'a']--;
            if (table[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
