package `1143`

import kotlin.math.max

/**
 * 如何将求 text1[0, i]、text2[0, j] 最长公共子序列问题转化为相同类型的子问题
 * 可以假设以下三个问题结果都是已知的，暂时不去想它们怎么来的，因为这也是问题的关键
 * text1[0, i-1]、text2[0, j-1]
 * text1[0, i-1]、text2[0, j]
 * text1[0, i]、text2[0, j-1]
 * 那么对于 text1[0, i]、text2[0, j] 而言
 * 如果 text1[i] == text2[j] 很明显结果就是 text1[0, i-1]、text2[0, j-1] 的结果 + 1
 * 如果 text1[i] != text2[j] 那么设想以下三种情况：
 * 1. text1[i] 不在公共子序列中，text2[j] 在公共子序列中，也就是 text2[j] 匹配到了 text1[i] 前面的元素，
 * 而其结果前面已经计算过了也就是 text1[0, i-1]、text2[0, j] 的结果。
 * 2. text1[i] 在公共子序列中，text2[j] 不在公共子序列中，也就是 text1[i] 匹配到了 text2[j] 前面的元素，
 * 而其结果前面已经计算过了也就是 text1[0, i]、text2[0, j-1] 的结果
 * 3. text1[i] 以及 text2[j] 都不在公共子序列中，其结果也计算过了就是 text1[i-1]、text2[j-1] 的结果，
 * 但是转念一想
 * text1[0, i-1]、text2[0, j-1] <= text1[0, i-1]、text2[0, j] &&
 * text1[0, i-1]、text2[0, j-1] <= text1[0, i]、text2[0, j-1]
 * 因此不相等的情况只需取 max(text1[0, i-1]、text2[0, j], text1[0, i]、text2[0, j-1]) 即可
 */
class Solution {
    fun longestCommonSubsequence(text1: String, text2: String): Int {
        val len1 = text1.length
        val len2 = text2.length
        val array = Array(len1 + 1) { IntArray(len2 + 1) }
        for (i in 1..len1) {
            for (j in 1..len2) {
                if (text1[i-1] == text2[j-1]) {
                    array[i][j] = array[i-1][j-1] + 1
                } else {
                    array[i][j] = max(array[i][j-1], array[i-1][j])
                }
            }
        }
        return array[len1][len2]
    }
}

fun main() {
    println(Solution().longestCommonSubsequence("abcde", "ace"))
}