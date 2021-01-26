package `679`

import kotlin.math.abs

/**
 * 计算 24 点
 * 首先从 4 个里面取出两个 2 个数字，然后从 4 个操作符中随机取一个，一共有 4 * 3 * 4 = 48
 * 把上述结果放到剩余两个数字中，再从 3 个里面取出 2 个数字，再取操作符一共有 3 * 2 * 4 = 24
 * 把上述结果放到剩余一个数字中，取从 2 个里面取出 2 个数字，再取操作符一共有 2 * 4 = 8
 * 48 * 24 * 8 = 9216 种，于是只要穷举了就行。
 */
class Solution {

    fun judgePoint24(nums: IntArray): Boolean {
        val list = ArrayList<Float>()
        for (num in nums) {
            list.add(num.toFloat())
        }
        return recursion(list)
    }

    private fun recursion(list: MutableList<Float>): Boolean {
        if (list.size == 1) {
            return abs(24 - list.first()) < 0.001
        }
        val copyList = ArrayList(list)
        // 枚举所有取两个数字的情况
        for (i in 0..list.lastIndex) {
            for (j in 0..list.lastIndex) {
                if (i != j) {
                    val first = list[i]
                    val second = list[j]
                    if (i > j) {
                        copyList.removeAt(i)
                        copyList.removeAt(j)
                    } else {
                        copyList.removeAt(i)
                        copyList.removeAt(j - 1)
                    }
                    val r1 = recursion(ArrayList(copyList).apply {
                        add(first + second)
                    })
                    val r2 = recursion(ArrayList(copyList).apply {
                        add(first - second)
                    })
                    val r3 = recursion(ArrayList(copyList).apply {
                        add(first * second)
                    })
                    val r4 = recursion(ArrayList(copyList).apply {
                        add(first / second)
                    })
                    if (r1 || r2 || r3 || r4) {
                        return true
                    }
                    copyList.clear()
                    copyList.addAll(list)
                }
            }
        }
        return false
    }
}

fun main() {
    println(Solution().judgePoint24(intArrayOf(3, 3, 8, 8)))
}