package `102`

import utils.bean.TreeNode
import java.util.*

/**
 * 层序，一层层遍历，BFS，使用两个队列，类似 GC 的两个存活区
 */
class Solution {
    fun levelOrder(root: TreeNode?): List<List<Int>> {
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
                resultList.add(mutableListOf())
            }
            val node = fromQueue.poll()
            resultList[level].add(node.`val`)
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
    println(Solution().levelOrder(TreeNode(1).apply {
        right = TreeNode(2).apply {
            left = TreeNode(3)
        }
    }))
}