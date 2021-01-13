package `654`

import bean.TreeNode

/**
 * 类似快排思想
 */
class Solution {
    fun constructMaximumBinaryTree(nums: IntArray): TreeNode? {
        return recursion(nums, 0, nums.lastIndex)
    }

    fun recursion(nums: IntArray, low: Int, high: Int): TreeNode? {
        if (low > high) {
            return null
        }
        val index = findMaxIndex(nums, low, high)
        val node = TreeNode(nums[index])
        node.left = recursion(nums, low, index - 1)
        node.right = recursion(nums, index + 1, high)
        return node
    }

    private fun findMaxIndex(nums: IntArray, low: Int, high: Int): Int {
        var max = nums[low]
        var maxIndex = low
        for (i in (low + 1)..high) {
            val e = nums[i]
            if (max < e) {
                max = e
                maxIndex = i
            }
        }
        return maxIndex
    }
}

fun main() {
    val node = Solution().constructMaximumBinaryTree(intArrayOf(3,2,1,6,0,5))
    println(node)
}