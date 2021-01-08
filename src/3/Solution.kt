package `3`

import java.lang.Integer.max

class Solution {
    fun lengthOfLongestSubstring(string: String): Int {
        val set = hashSetOf<Char>()
        var left = 0
        var right = 0
        var max = 0
        while (right < string.length) {
            val char = string[right]
            if (set.contains(char)) {
                set.remove(string[left++])
            } else {
                right++
                set.add(char)
                max = max(max, set.size)
            }
        }
        return max
    }
}

fun main() {
    println(Solution().lengthOfLongestSubstring(" "))
}