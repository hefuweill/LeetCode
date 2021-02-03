package `94`

import utils.bean.TreeNode
import java.util.*

/**
 * 中序遍历 左中右 注意迭代需要先找到 origin
 */
class Solution {
    fun inorderTraversal(root: TreeNode?): List<Int> {
//        return iteration(root)
        return recursion(root)
    }

    private fun iteration(root: TreeNode?): List<Int> {
        val resultList = mutableListOf<Int>()
        val stack = Stack<TreeNode>()
        var currentNode = root
        while (currentNode != null || !stack.isEmpty()) {
            while (currentNode != null) {
                stack.add(currentNode)
                currentNode = currentNode.left
            }
            val node = stack.pop()
            resultList.add(node.`val`)
            currentNode = node.right
        }
        return resultList
    }

    private fun recursion(root: TreeNode?): List<Int> {
        val resultList = mutableListOf<Int>()
        realRecursion(root, resultList)
        return resultList
    }

    private fun realRecursion(node: TreeNode?, resultList: MutableList<Int>) {
        if (node == null) {
            return
        }
        realRecursion(node.left, resultList)
        resultList.add(node.`val`)
        realRecursion(node.right, resultList)
    }
}

fun main() {
    println(Solution().inorderTraversal(TreeNode(1).apply {
        right = TreeNode(2).apply {
            left = TreeNode(3)
        }
    }))
}