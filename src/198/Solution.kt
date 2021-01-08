package `198`

import kotlin.math.max

/**
 * https://leetcode-cn.com/problems/house-robber/
 * 假设只有一家也就是 result[0] = nums[0]
 * 假设有两家那么 result[1] = max(result[0], nums[1])
 * 假设有三家那么 result[2] = max(result[1], nums[2] + result[0])
 * 推导 dp 方程为 result[n] = max(result[n-1], nums[n] + result[n-2])
 */
class Solution {
    fun rob(nums: IntArray): Int {
        if (nums.isEmpty()) {
            return 0
        }
        if (nums.size == 1) {
            return nums[0]
        }
        var first = nums[0]
        var second = max(nums[1], first)
        for (i in 2 until nums.size) {
            val temp = second
            second = max(second, first + nums[i])
            first = temp
        }
        return second
    }
}

fun main() {
    println(Solution().rob(intArrayOf(2,7,9,3,1)))
}