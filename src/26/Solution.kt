package `26`

/**
 * 暴力法，效率非常低
 * 快慢指针，方便且效率高
 */
class Solution {
    fun removeDuplicates(nums: IntArray): Int {
        return solution2(nums)
    }

    private fun solution1(nums: IntArray): Int {
        var preview = Int.MAX_VALUE
        var index = 0
        var end = nums.lastIndex
        while (index <= end) {
            val value = nums[index]
            if (value == preview) {
                for (j in index + 1..nums.lastIndex) {
                    nums[j - 1] = nums[j]
                }
                end--
            } else {
                index++
            }
            preview = value
        }
        return end + 1
    }

    private fun solution2(nums: IntArray): Int {
        if (nums.isEmpty()) {
            return 0
        }
        var low = 0
        for (index in 1..nums.lastIndex) {
            if (nums[low] != nums[index]) {
                low++
                nums[low] = nums[index]
            }
        }
        return low + 1
    }
}

fun main() {
    val param = intArrayOf(0, 1, 1, 2, 3, 4, 4, 4, 6, 6, 7)
    println(Solution().removeDuplicates(param))
    println(param.contentToString())
}