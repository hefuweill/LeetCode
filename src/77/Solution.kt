package `77`

/**
 * 组合
 * 1 ~ n 取 k 个字符
 */
class Solution {

    fun combine(n: Int, k: Int): List<List<Int>> {
        val list = ArrayList<List<Int>>()
        backtrack(n, k, 1, arrayListOf(), list)
        return list
    }

    private fun backtrack(n: Int, k: Int, startIndex: Int, list: MutableList<Int>, resultList: MutableList<List<Int>>) {
        if (list.size == k) {
            resultList.add(ArrayList(list))
            return
        }
        for (i in startIndex..n) {
            list.add(i)
            backtrack(n, k, i + 1, list, resultList)
            list.removeAt(list.lastIndex)
        }
    }
}