package `10`

/**
 * 将大问题转换为相同类型的小问题，着眼点为 string[0, i]、pattern[0, j]
 * 如何根据已知内容推导出 string[0, i]、pattern[0, j] 是否匹配
 */
class Solution {
    fun isMatch(string: String, pattern: String): Boolean {
        val sLen = string.length
        val pLen = pattern.length
        // array[i][j] 表示 string 前 i 个字符是否与 pattern 前 j 个字符匹配
        val array = Array(sLen + 1) { BooleanArray(pLen + 1) }
        // string 前 0 个字符与 pattern 前 0 个字符当然匹配
        array[0][0] = true
        // 计算 string 为空串 pattern 不为空串的情况，无需计算 string 不为空串 pattern 为空串情况
        for (j in 1..pLen) {
            // 如果是 * 号那么去除 ?* 后判断是否匹配，防止出现 s: '' p: z*
            if (pattern[j-1] == '*') {
                // 无需考虑越界 * 前必须要有字符
                array[0][j] = array[0][j-2]
            }
        }
        // 计算 string 和 pattern 都不为空串的情况
        for (i in 1..sLen) {
            for (j in 1..pLen) {
                // 如果当前两个字符相同，那么结果就是 string[0, i-1] 和 pattern[0, j-1] 的结果
                if (string[i-1] == pattern[j-1] || pattern[j-1] == '.') {
                    array[i][j] = array[i-1][j-1]
                } else if (pattern[j-1] == '*') {
                    // 当 pattern[j-1] 为 * 号还是有可能匹配的如：i j 都为 3 ，abb ab*
                    if (string[i-1] == pattern[j-2] || pattern[j-2] == '.') {
                        // * 号 可以抵消 0 ~ N 个 pattern[j-2]
                        array[i][j] = array[i][j-2] || array[i-1][j-2] || array[i-1][j]
                    } else {
                        // 只有 * 号抵消 0 个 pattern[j - 2] 时才有可能匹配
                        array[i][j] = array[i][j-2]
                    }
                }
            }
        }
        return array[sLen][pLen]
    }
}

fun main() {
    println(Solution().isMatch("asddfg", "asd*dfg"))
}