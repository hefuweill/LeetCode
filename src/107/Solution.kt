package `107`

import bean.TreeNode
import java.util.*

/**
 * 102 题的逆序，简单修改下每次新建列表放到前面就行
 */
class Solution {

    fun levelOrderBottom(root: TreeNode?): List<List<Int>> {
        val resultList = mutableListOf<MutableList<Int>>()
        if (root == null) {
            return resultList
        }
        var level = 0
        var fromQueue = LinkedList<TreeNode>()
        var toQueue = LinkedList<TreeNode>()
        fromQueue.add(root)
        while (fromQueue.isNotEmpty()) {
            if (resultList.size <= level) {
                resultList.add(0, mutableListOf())
            }
            val node = fromQueue.poll()
            resultList[resultList.size - level - 1].add(node.`val`)
            if (node.left != null) {
                toQueue.offer(node.left)
            }
            if (node.right != null) {
                toQueue.offer(node.right)
            }
            if (fromQueue.isEmpty()) {
                val temp = fromQueue
                fromQueue = toQueue
                toQueue = temp
                level++
            }
        }
        return resultList
    }
}

fun main() {
    println(Solution().levelOrderBottom(TreeNode(1).apply {
        right = TreeNode(2).apply {
            left = TreeNode(3)
        }
    }))
}