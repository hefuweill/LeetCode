package `1`

class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val map = mutableMapOf<Int, Int>()
        for ((index, num) in nums.withIndex()) {
            if (map.containsKey(num)) {
                return intArrayOf(map[num]!!, index)
            }
            map[target - num] = index
        }
        return intArrayOf()
    }
}

fun main() {
    val array = intArrayOf(2, 7, 11, 15)
    val result = Solution().twoSum(array, 18)
    println(result.contentToString())
}