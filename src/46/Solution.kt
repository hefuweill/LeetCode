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
 * 第一种方式时间效率不高，如果想高效可以使用标记数组（第二种方式空间效率不高）
 * 如果想两者兼顾，那么只能动态交换了（暂时忽略）
 */
class Solution {

    fun permute(nums: IntArray): List<List<Int>> {
//        return recursion1(nums, LinkedList(), arrayListOf())
        return recursion2(nums, BooleanArray(nums.size), LinkedList(), arrayListOf())
    }

    /**
     * 查找法
     */
    private fun recursion1(nums: IntArray, list: LinkedList<Int>, resultList: MutableList<List<Int>>): List<List<Int>> {
        if (list.size == nums.size) {
            resultList.add(ArrayList(list))
            return resultList
        }
        for (i in 0..nums.lastIndex) {
            if (list.contains(nums[i])) {
                continue
            }
            list.add(nums[i])
            recursion1(nums, list, resultList)
            list.removeLast()
        }
        return resultList
    }

    /**
     * 标记法
     */
    private fun recursion2(nums: IntArray, markNums: BooleanArray, list: LinkedList<Int>, resultList: MutableList<List<Int>>): List<List<Int>> {
        if (list.size == nums.size) {
            resultList.add(ArrayList(list))
            return resultList
        }
        for (i in 0..nums.lastIndex) {
            if (markNums[i]) {
                continue
            }
            list.add(nums[i])
            markNums[i] = true
            recursion2(nums, markNums, list, resultList)
            markNums[i] = false
            list.removeLast()
        }
        return resultList
    }
}

fun main() {
    println(Solution().permute(intArrayOf(1, 2, 3)))
}