package `145`

import utils.bean.TreeNode
import java.util.*

/**
 * 后序遍历 左右中 迭代时注意回溯，挺难的
 */
class Solution {

    fun postorderTraversal(root: TreeNode?): List<Int> {
//        return recursion(root)
        return iteration(root)
    }

    private fun iteration(root: TreeNode?): List<Int> {
        val resultList = mutableListOf<Int>()
        val stack = Stack<TreeNode>()
        var currentNode: TreeNode? = root
        var previewNode: TreeNode? = null
        while (!stack.isEmpty() || currentNode != null) {
            while (currentNode != null) {
                stack.push(currentNode)
                currentNode = currentNode.left
            }
            val node = stack.peek()
            if (node.right != null && node.right != previewNode) {
                currentNode = node.right
            } else {
                previewNode = node
                resultList.add(stack.pop().`val`)
            }
        }
        return resultList
    }

    private fun recursion(node: TreeNode?): List<Int> {
        val resultList = mutableListOf<Int>()
        realRecursion(node, resultList)
        return resultList
    }

    private fun realRecursion(node: TreeNode?, resultList: MutableList<Int>) {
        if (node == null) {
            return
        }
        realRecursion(node.left, resultList)
        realRecursion(node.right, resultList)
        resultList.add(node.`val`)
    }
}

fun main() {
    println(Solution().postorderTraversal(TreeNode(1).apply {
        right = TreeNode(2).apply {
            left = TreeNode(3)
        }
    }))
}