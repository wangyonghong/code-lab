package me.yonghong.algo.lc.lc0301

class Lc0383Kt {
    fun canConstruct(ransomNote: String, magazine: String): Boolean {
        val table = IntArray(26)
        for (c in magazine.toCharArray()) {
            table[c.code - 'a'.code]++
        }
        for (c in ransomNote.toCharArray()) {
            table[c.code - 'a'.code]--
            if (table[c.code - 'a'.code] < 0) {
                return false
            }
        }
        return true
    }
}