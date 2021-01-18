package `332`

import java.lang.Integer.min

/**
 * 硬币无限，求最小需要几个硬币凑到目标值
 * 写个递归，把所有情况都考虑到，不过性能不佳，超出时间限制
 * 考虑加个缓存，防止重复计算，这回通过了（自顶向下）
 */
class Solution {

    fun coinChange(coins: IntArray, amount: Int): Int {
        return recursion(IntArray(amount + 1), coins, amount, 0)
    }

    private fun recursion(dp: IntArray, coins: IntArray, amount: Int, coinAmount: Int): Int {
        if (amount == 0) {
            return 0
        } else if (amount < 0) {
            return -1
        }
        if (dp[amount] != 0) {
            return dp[amount]
        }
        var min = Int.MAX_VALUE
        for (coin in coins) {
            val result = recursion(dp, coins, amount - coin, coinAmount)
            if (result != -1) {
                min = min(min, result + 1)
            }
        }
        dp[amount] = if (min == Int.MAX_VALUE) {
            -1
        } else {
            min
        }
        return dp[amount]
    }
}

fun main() {
    println(Solution().coinChange(intArrayOf(1, 2, 5), 100))
}