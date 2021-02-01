package `51`

import java.lang.IllegalArgumentException
import java.lang.StringBuilder

/**
 * N 皇后
 * 每一行最多只能选择一个，那么有 N 行最多为 N 个皇后
 * 其实和全排列差不多，比如第一行选第一个，第二行选第一个... 每次选择完毕后判断下是否合法
 *
 * 回溯框架，先选择一种情况，判断是否合法，如果合法走下去，走完后选择下一种方式
 */
class Solution {

    fun solveNQueens(n: Int): List<List<String>> {
        val resultList = mutableListOf<MutableList<String>>()
        if (n < 0) {
            throw IllegalArgumentException()
        } else if (n == 0) {
            return resultList
        }
        recursion(n, arrayListOf(), resultList)
        return resultList
    }

    private fun recursion(n: Int, list: MutableList<String>, resultList: MutableList<MutableList<String>>) {
        if (list.size == n) {
            resultList.add(ArrayList(list))
            return
        }
        for (i in 0 until n) {
            // 该位置放置皇后是否合法
            if (!isLegal(list, n, list.size, i)) {
                continue
            }
            list.add(build(i, n))
            recursion(n, list, resultList)
            list.removeAt(list.lastIndex)
        }
    }

    /**
     * [row, column] 这个位置放置 N 皇后是否合法，不合法切换下一种方案
     * 比对 竖向、左上、右上，不需要考虑横向
     */
    private fun isLegal(list: MutableList<String>, n: Int, row: Int, column: Int): Boolean {
        // 竖向排除
        for (i in 0 until row) {
            if (list[i][column] == 'Q') {
                return false
            }
        }
        var i = row - 1
        var j = column - 1
        while (i >= 0 && j >= 0) {
            if (list[i][j] == 'Q') {
                return false
            }
            i -= 1
            j -= 1
        }
        i = row - 1
        j = column + 1
        while (i >= 0 && j < n) {
            if (list[i][j] == 'Q') {
                return false
            }
            i -= 1
            j += 1
        }
        return true
    }

    private fun build(i: Int, n: Int): String {
        val builder = StringBuilder()
        for (index in 0 until n) {
            if (i == index) {
                builder.append("Q")
            } else {
                builder.append(".")
            }
        }
        return builder.toString()
    }

}

fun main() {
    println(Solution().solveNQueens(14))
}