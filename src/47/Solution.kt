package `47`

import java.util.*
import kotlin.collections.ArrayList

/**
 * 给定 [1, 1, 2] 返回不重复全排列，数字可以重复
 * 核心问题是如何高效的去重，对于同一个位置如果已经有相同元素计算过了就需要跳过该元素
 * 比如对于 1，1，2 首位元素如果 1 已经计算过了，第二个 1 就需要跳过
 */
class Solution {

    fun permuteUnique(nums: IntArray): List<List<Int>> {
        return recursion(nums.apply { sort() }, BooleanArray(nums.size), LinkedList(), arrayListOf())
    }

    /**
     * 标记法
     */
    private fun recursion(nums: IntArray, markNums: BooleanArray, list: LinkedList<Int>, resultList: MutableList<List<Int>>): List<List<Int>> {
        if (list.size == nums.size) {
            resultList.add(ArrayList(list))
            return resultList
        }
        for (i in 0..nums.lastIndex) {
            if (markNums[i]) {
                continue
            }
            if (i > 0 && nums[i] == nums[i-1] && !markNums[i-1]) {
                continue
            }
            list.add(nums[i])
            markNums[i] = true
            recursion(nums, markNums, list, resultList)
            markNums[i] = false
            list.removeLast()
        }
        return resultList
    }
}

fun main() {
    println(Solution().permuteUnique(intArrayOf(1, 1, 2)))
}