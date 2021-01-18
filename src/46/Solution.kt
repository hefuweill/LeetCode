package `46`

import java.util.*
import kotlin.collections.ArrayList

/**
 * 给定 [1, 2, 3] 返回全排列，数字需要不重复
 * [1, 2, 3]
 * [1, 3, 2]
 * [2, 1, 3]
 * [2, 3, 1]
 * [3, 1, 2]
 * [3, 2, 1]
 * 需要记住放入结果时需要拷贝一份，不然后续又会移除
 */
class Solution {

    fun permute(nums: IntArray): List<List<Int>> {
        return recursion(nums, LinkedList(), arrayListOf())
    }

    private fun recursion(nums: IntArray, list: LinkedList<Int>, resultList: MutableList<List<Int>>): List<List<Int>> {
        if (list.size == nums.size) {
            resultList.add(ArrayList(list))
            return resultList
        }
        for (i in 0..nums.lastIndex) {
            if (list.contains(nums[i])) {
                continue
            }
            list.add(nums[i])
            recursion(nums, list, resultList)
            list.removeLast()
        }
        return resultList
    }
}

fun main() {
    println(Solution().permute(intArrayOf(1, 2, 3)))
}