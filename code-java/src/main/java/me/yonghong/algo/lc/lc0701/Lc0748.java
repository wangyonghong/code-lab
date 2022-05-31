package me.yonghong.algo.lc.lc0701;

import me.yonghong.algo.Solution;

import java.util.Arrays;

/**
 * 748. 最短补全词
 *
 * @author yonghongwang#163.com
 * @link <a href="https://leetcode.cn/problems/shortest-completing-word/"></a>
 * @link <a href="https://leetcode.com/problems/shortest-completing-word/"></a>
 * @since 2021/12/10
 **/
class Lc0748 implements Solution {

    public String shortestCompletingWord(String licensePlate, String[] words) {
        int[] cnts = new int[26];
        for (char ch : licensePlate.toCharArray()) {
            if (Character.isLetter(ch)) {
                cnts[Character.toLowerCase(ch) - 'a']++;
            }
        }
        int k = -1;
        for (int i = 0; i < words.length; i++) {
            if (isValid(words[i], Arrays.copyOf(cnts, cnts.length))) {
                if (k == -1 || words[i].length() < words[k].length()) {
                    k = i;
                }
            }
        }
        return words[k];
    }

    private boolean isValid(String word, int[] cnts) {
        for (char ch : word.toCharArray()) {
            cnts[ch - 'a']--;
        }
        for (int cnt : cnts) {
            if (cnt > 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void test() {
        String res;
        res = shortestCompletingWord("1s3 PSt",
                new String[]{"step", "steps", "stripe", "stepple"});
        System.out.println(res);
        res = shortestCompletingWord("Ah71752",
                new String[]{"suggest", "letter", "of", "husband", "easy", "education", "drug", "prevent", "writer", "old"});
        System.out.println(res);
    }

    public static void main(String[] args) {
        new Lc0748().test();
    }
}
