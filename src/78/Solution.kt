package `78`

/**
 * 子集，输入 nums 各个元素都不同，输出子集要求不重复
 */
class Solution {

    fun subsets(nums: IntArray): List<List<Int>> {
        return backtrack(nums, 0, arrayListOf(), arrayListOf())
    }

    private fun backtrack(nums: IntArray, startIndex: Int, list: MutableList<Int>, resultList: MutableList<List<Int>>): List<List<Int>> {
        resultList.add(ArrayList(list))
        if (list.size == nums.size) {
            return resultList
        }
        for (i in startIndex..nums.lastIndex) {
            list.add(nums[i])
            backtrack(nums, i + 1, list, resultList)
            list.removeAt(list.lastIndex)
        }
        return resultList
    }
}